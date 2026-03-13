import { Component, Input, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  @Input() hideNav = false;
  items: MenuItem[] = [];
  menuOpen = false;

  ngOnInit() {

    this.items = [
      {
        label: 'Se déconnecter',
        styleClass: 'nav-link--logout'
      },
      {
        label: 'Articles',
        icon: 'pi pi-file',
        routerLink: '/feed'
      },
      {
        label: 'Thèmes',
        icon: 'pi pi-tag',
        routerLink: '/themes'
      }
    ];
  }

}
