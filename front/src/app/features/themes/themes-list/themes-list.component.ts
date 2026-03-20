import { Component, OnDestroy, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { Subject } from 'rxjs';
import { forkJoin } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { ThemeResponse } from 'src/app/core/models/theme.model';
import { SubscriptionService } from 'src/app/core/services/subscription.service';
import { ThemeService } from 'src/app/core/services/theme.service';

@Component({
  selector: 'app-themes-list',
  templateUrl: './themes-list.component.html',
  styleUrls: ['./themes-list.component.scss']
})
export class ThemesListComponent implements OnInit, OnDestroy {

  themes: ThemeResponse[] = [];
  subscribedIds = new Set<number>();
  private destroy$ = new Subject<void>();

  constructor(
    private themeService: ThemeService,
    private subscriptionService: SubscriptionService,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    forkJoin({
      themes: this.themeService.getAll(),
      subscriptions: this.subscriptionService.getUserSubscriptions()
    })
    .pipe(takeUntil(this.destroy$))
    .subscribe({
      next: ({ themes, subscriptions }) => {
        this.themes = themes;
        this.subscribedIds = new Set(subscriptions.map(s => s.id));
      },
      error: () => {
        this.messageService.add({
          severity: 'error',
          summary: 'Erreur',
          detail: 'Impossible de charger les thèmes'
        });
      }
    });
  }

  isSubscribed(themeId: number): boolean {
    return this.subscribedIds.has(themeId);
  }

  subscribe(themeId: number): void {
    this.subscriptionService.subscribe(themeId)
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: () => {
          this.subscribedIds = new Set([...this.subscribedIds, themeId]);
          this.messageService.add({
            severity: 'success',
            summary: 'Abonnement enregistré',
          });
        },
        error: () => {
          this.messageService.add({
            severity: 'error',
            summary: 'Erreur',
            detail: 'Impossible de s\'abonner'
          });
        }
      });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
