import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { PostResponse } from 'src/app/core/models/post.model';
import { CommentResponse } from 'src/app/core/models/comment.model';
import { PostService } from 'src/app/core/services/post.service';
import { AuthService } from 'src/app/core/services/auth.service';
import { CommentService } from 'src/app/core/services/comment.service';

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.scss']
})
export class PostDetailComponent implements OnInit, OnDestroy {

  post: PostResponse | null = null;
  comments: CommentResponse[] = [];
  commentBody = '';
  postId!: number;
  private destroy$ = new Subject<void>();

  constructor(
    private route: ActivatedRoute,
    private postService: PostService,
    private commentService: CommentService,
    private authService: AuthService,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    this.postId = Number(this.route.snapshot.paramMap.get('id'));
    this.loadPost();
    this.loadComments();
  }

  loadPost(): void {
    this.postService.getById(this.postId)
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (data) => { this.post = data; },
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
    this.commentService.getByPost(this.postId)
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (data) => { this.comments = data; },
        error: () => {}
      });
  }

  submitComment(): void {
    if (!this.commentBody.trim()) return;
    const authorId = this.authService.getUserId() || '';
    this.commentService.create({ body: this.commentBody, authorId, postId: this.postId })
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
