import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { ArticleService } from 'src/app/core/services/article.service';
import { AuthService } from 'src/app/core/services/auth.service';
import { ThemeService } from 'src/app/core/services/theme.service';
import { ThemeResponse } from 'src/app/core/models/theme.model';

@Component({
  selector: 'app-create-form',
  templateUrl: './create-form.component.html',
  styleUrls: ['./create-form.component.scss']
})
export class CreateFormComponent implements OnInit, OnDestroy {

  form: FormGroup;
  themes: ThemeResponse[] = [];
  isSubmitting = false;
  private destroy$ = new Subject<void>();

  constructor(
    private fb: FormBuilder,
    private articleService: ArticleService,
    private themeService: ThemeService,
    private authService: AuthService,
    private messageService: MessageService,
    private router: Router
  ) {
    this.form = this.fb.group({
      themeId: [null, Validators.required],
      title: ['', Validators.required],
      content: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.themeService.getAll()
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (data) => { this.themes = data; },
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
    this.articleService.create({ ...this.form.value, authorId })
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (article) => {
          this.router.navigate(['/article/detail', article.id]);
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
