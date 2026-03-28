import { Component, OnDestroy, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { Subject } from 'rxjs';
import { forkJoin } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { TopicResponse } from 'src/app/core/models/topic.model';
import { SubscriptionService } from 'src/app/core/services/subscription.service';
import { TopicService } from 'src/app/core/services/topic.service';

@Component({
  selector: 'app-topics-list',
  templateUrl: './topics-list.component.html',
  styleUrls: ['./topics-list.component.scss']
})
export class TopicsListComponent implements OnInit, OnDestroy {

  topics: TopicResponse[] = [];
  subscribedIds = new Set<number>();
  private destroy$ = new Subject<void>();

  constructor(
    private topicService: TopicService,
    private subscriptionService: SubscriptionService,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    forkJoin({
      topics: this.topicService.getAll(),
      subscriptions: this.subscriptionService.getUserSubscriptions()
    })
    .pipe(takeUntil(this.destroy$))
    .subscribe({
      next: ({ topics, subscriptions }) => {
        this.topics = topics;
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

  isSubscribed(topicId: number): boolean {
    return this.subscribedIds.has(topicId);
  }

  subscribe(topicId: number): void {
    this.subscriptionService.subscribe(topicId)
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: () => {
          this.subscribedIds = new Set([...this.subscribedIds, topicId]);
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
