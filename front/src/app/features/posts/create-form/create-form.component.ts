import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { PostService } from 'src/app/core/services/post.service';
import { AuthService } from 'src/app/core/services/auth.service';
import { TopicService } from 'src/app/core/services/topic.service';
import { TopicResponse } from 'src/app/core/models/topic.model';

@Component({
  selector: 'app-create-form',
  templateUrl: './create-form.component.html',
  styleUrls: ['./create-form.component.scss']
})
export class CreateFormComponent implements OnInit, OnDestroy {

  form: FormGroup;
  topics: TopicResponse[] = [];
  isSubmitting = false;
  private destroy$ = new Subject<void>();

  constructor(
    private fb: FormBuilder,
    private postService: PostService,
    private topicService: TopicService,
    private authService: AuthService,
    private messageService: MessageService,
    private router: Router
  ) {
    this.form = this.fb.group({
      topicId: [null, Validators.required],
      title: ['', Validators.required],
      content: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.topicService.getAll()
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (data) => { this.topics = data; },
        error: () => {
          this.messageService.add({
            severity: 'error',
            summary: 'Erreur',
            detail: 'Impossible de charger les thèmes'
          });
        }
      });
  }

  onSubmit(): void {
    if (this.form.invalid) return;
    this.isSubmitting = true;
    const authorId = this.authService.getUserId() || '';
    this.postService.create({ ...this.form.value, authorId })
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (post) => {
          this.messageService.add({
            severity: 'success',
            summary: 'Article créé avec succès'
          });
          this.router.navigate(['/post/detail', post.id]);
        },
        error: () => {
          this.messageService.add({
            severity: 'error',
            summary: 'Erreur',
            detail: 'Impossible de créer l\'article'
          });
          this.isSubmitting = false;
        }
      });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
