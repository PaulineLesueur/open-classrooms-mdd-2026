import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { MessageService } from 'primeng/api';
import { ArticleService } from 'src/app/core/services/article.service';
import { ArticleResponse } from 'src/app/core/models/article.model';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.scss']
})
export class FeedComponent implements OnInit, OnDestroy {

  articles: ArticleResponse[] = [];
  isLoading = false;
  selectedSort = 'desc';
  private destroy$ = new Subject<void>();

  constructor(
    private articleService: ArticleService,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    this.isLoading = true;
    this.articleService.getAll()
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (data) => {
          this.articles = data.sort((a, b) =>
            new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime()
          );
          this.isLoading = false;
        },
        error: () => {
          this.messageService.add({
            severity: 'error',
            summary: 'Erreur',
            detail: 'Impossible de charger les articles'
          });
          this.isLoading = false;
        }
      });
  }

  onSortChange(): void {
    this.selectedSort = this.selectedSort === 'desc' ? 'asc' : 'desc';
    this.articles = [...this.articles].sort((a, b) => {
      const diff = new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime();
      return this.selectedSort === 'asc' ? -diff : diff;
    });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
