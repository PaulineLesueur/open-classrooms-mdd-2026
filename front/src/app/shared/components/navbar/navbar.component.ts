import { Component, Input, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { AuthService } from '../../../core/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  @Input() hideNav = false;
  items: MenuItem[] = [];
  menuOpen = false;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit() {

    this.items = [
      {
        label: 'Se déconnecter',
        styleClass: 'nav-link--logout',
        command: () => this.logout()
      },
      {
        label: 'Articles',
        icon: 'pi pi-file',
        routerLink: '/feed'
      },
      {
        label: 'Thèmes',
        icon: 'pi pi-tag',
        routerLink: '/topics'
      }
    ];
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/']);
  }

}
