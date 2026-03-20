import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
 form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private messageService: MessageService
  ) {
    this.form = this.fb.group({
      identifier: ['', Validators.required],
      password: ['', Validators.required]
    });
   }

  ngOnInit(): void { }

  onSubmit(): void  {
    if (this.form.invalid)
      return;

    this.authService.login(this.form.value).subscribe({
      next: (response) => {
        this.authService.saveUser(response);
        this.router.navigate(['/feed']);
      },
      error: (err) => {
        this.messageService.add({
            severity: 'error',
            summary: 'Échec de la connexion',
            detail: 'Le nom d\'utilisateur ou le mot de passe semble incorrect'
        });
      }
    })
  }
}
