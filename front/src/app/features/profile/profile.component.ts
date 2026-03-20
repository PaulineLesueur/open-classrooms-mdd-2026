import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { TopicResponse } from '../../core/models/topic.model';
import { AuthService } from '../../core/services/auth.service';
import { SubscriptionService } from '../../core/services/subscription.service';
import { UserService } from '../../core/services/user.service';
import { passwordValidator } from '../../core/validators/password.validator';

@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit, OnDestroy {

    form: FormGroup;
    userId: string = '';
    subscriptions: TopicResponse[] = [];
    private destroy$ = new Subject<void>();

    constructor(
        private fb: FormBuilder,
        private authService: AuthService,
        private userService: UserService,
        private subscriptionService: SubscriptionService,
        private messageService: MessageService
    ) {
        this.form = this.fb.group({
            username: ['', Validators.required],
            email: ['', [Validators.required, Validators.email]],
            password: ['', [passwordValidator]]
        });
    }

    ngOnInit(): void {
        this.userId = this.authService.getUserId() || '';
        this.form.patchValue({
            username: this.authService.getUsername(),
            email: this.authService.getEmail()
        });
        this.loadSubscriptions();
    }

    loadSubscriptions(): void {
        this.subscriptionService.getUserSubscriptions()
            .pipe(takeUntil(this.destroy$))
            .subscribe({
                next: (data) => { this.subscriptions = data; },
                error: () => {
                    this.messageService.add({
                        severity: 'error',
                        summary: 'Erreur',
                        detail: 'Impossible de charger les abonnements'
                    });
                }
            });
    }

    unsubscribe(topicId: number): void {
        this.subscriptionService.unsubscribe(topicId)
            .pipe(takeUntil(this.destroy$))
            .subscribe({
                next: () => {
                    this.subscriptions = this.subscriptions.filter(s => s.id !== topicId);
                    this.messageService.add({
                        severity: 'success',
                        summary: 'Désabonnement enregistré',
                    });
                },
                error: () => {
                    this.messageService.add({
                        severity: 'error',
                        summary: 'Erreur',
                        detail: 'Erreur lors du désabonnement'
                    });
                }
            });
    }

    onSubmit(): void {
        const { username, email, password } = this.form.value;

        const profileUpdate = this.userService.update(this.userId, { username, email });
        profileUpdate.subscribe({
            next: () => {
                this.authService.saveUser({
                    token: this.authService.getToken() || '',
                    id: this.userId,
                    username,
                    email
                });

                if (password) {
                    this.userService.updatePassword(this.userId, { password }).subscribe({
                        next: () => {
                            this.messageService.add({
                                severity: 'success',
                                summary: 'Profil mis à jour avec succès'
                            });
                        },
                        error: () => {
                            this.messageService.add({
                                severity: 'error',
                                summary: 'Erreur',
                                detail: 'Erreur lors de la mise à jour du mot de passe'
                            });
                        }
                    });
                } else {
                    this.messageService.add({
                        severity: 'success',
                        summary: 'Profil mis à jour avec succès'
                    });
                }
            },
            error: (err) => {
                const message = err.error?.message || '';
                if (message.includes('Username')) {
                    this.messageService.add({
                        severity: 'error',
                        summary: 'Erreur',
                        detail: 'Nom d\'utilisateur déjà utilisé'
                    });
                } else if (message.includes('Email')) {
                    this.messageService.add({
                        severity: 'error',
                        summary: 'Erreur',
                        detail: 'Email déjà utilisé'
                    });
                } else {
                    this.messageService.add({
                        severity: 'error',
                        summary: 'Erreur',
                        detail: 'Une erreur est survenue'
                    });
                }
            }
        });
    }

    ngOnDestroy(): void {
        this.destroy$.next();
        this.destroy$.complete();
    }
}