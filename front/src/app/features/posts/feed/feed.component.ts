import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { MessageService } from 'primeng/api';
import { PostService } from 'src/app/core/services/post.service';
import { PostResponse } from 'src/app/core/models/post.model';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.scss']
})
export class FeedComponent implements OnInit, OnDestroy {

  posts: PostResponse[] = [];
  isLoading = false;
  selectedSort = 'desc';
  private destroy$ = new Subject<void>();

  constructor(
    private postService: PostService,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    this.isLoading = true;
    this.postService.getAll()
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (data) => {
          this.posts = data.sort((a, b) =>
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
    this.posts = [...this.posts].sort((a, b) => {
      const diff = new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime();
      return this.selectedSort === 'asc' ? -diff : diff;
    });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
