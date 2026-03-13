import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

    form: FormGroup;

    constructor(
        private fb: FormBuilder,
        private authService: AuthService,
        private router: Router,
        private messageService: MessageService
    ) {
        this.form = this.fb.group({
            username: ['', Validators.required],
            email: ['', [Validators.required, Validators.email]],
            password: ['', Validators.required]
        });
    }

    onSubmit(): void {
        if (this.form.invalid) return;
        this.authService.register(this.form.value).subscribe({
            next: (response) => {
                this.authService.saveToken(response.token);
                this.messageService.add({
                    severity: 'success',
                    detail: 'Compte créé avec succès !'
                });
                setTimeout(() => this.router.navigate(['/feed']), 1500);
            },
            error: (err) => {
                const message = err.error?.message || '';
                if (message.includes('Username')) {
                    this.messageService.add({
                        severity: 'error',
                        detail: 'Nom d\'utilisateur déjà utilisé'
                    });
                } else if (message.includes('Email')) {
                    this.messageService.add({
                        severity: 'error',
                        detail: 'Email déjà utilisé'
                    });
                } else {
                    this.messageService.add({
                        severity: 'error',
                        summary: 'Erreur',
                        detail: 'Une erreur est survenue, veuillez réessayer'
                    });
                }
            }
        });
    }
}