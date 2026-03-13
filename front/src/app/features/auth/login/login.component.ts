import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
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
    private router: Router
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
        this.authService.saveToken(response.token);
        this.router.navigate(['/feed']);
      },
      error: (err) => {
        console.error('Login failed', err);
      }
    })
  }
}
