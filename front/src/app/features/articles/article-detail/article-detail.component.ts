import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { ArticleResponse } from 'src/app/core/models/article.model';
import { CommentResponse } from 'src/app/core/models/comment.model';
import { ArticleService } from 'src/app/core/services/article.service';
import { AuthService } from 'src/app/core/services/auth.service';
import { CommentService } from 'src/app/core/services/comment.service';

@Component({
  selector: 'app-article-detail',
  templateUrl: './article-detail.component.html',
  styleUrls: ['./article-detail.component.scss']
})
export class ArticleDetailComponent implements OnInit, OnDestroy {

  article: ArticleResponse | null = null;
  comments: CommentResponse[] = [];
  commentBody = '';
  articleId!: number;
  private destroy$ = new Subject<void>();

  constructor(
    private route: ActivatedRoute,
    private articleService: ArticleService,
    private commentService: CommentService,
    private authService: AuthService,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    this.articleId = Number(this.route.snapshot.paramMap.get('id'));
    this.loadArticle();
    this.loadComments();
  }

  loadArticle(): void {
    this.articleService.getById(this.articleId)
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (data) => { this.article = data; },
        error: () => {
          this.messageService.add({
            severity: 'error',
            summary: 'Erreur',
            detail: 'Impossible de charger l\'article'
          });
        }
      });
  }

  loadComments(): void {
    this.commentService.getByArticle(this.articleId)
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (data) => { this.comments = data; },
        error: () => {}
      });
  }

  submitComment(): void {
    if (!this.commentBody.trim()) return;
    const authorId = this.authService.getUserId() || '';
    this.commentService.create({ body: this.commentBody, authorId, articleId: this.articleId })
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (comment) => {
          this.comments = [...this.comments, comment];
          this.commentBody = '';
        },
        error: () => {
          this.messageService.add({
            severity: 'error',
            summary: 'Erreur',
            detail: 'Impossible d\'envoyer le commentaire'
          });
        }
      });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
