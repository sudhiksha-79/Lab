import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  template: `
    <nav class="navbar">
      <a class="navbar-brand" routerLink="/">
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" width="24" height="24">
          <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"/>
          <circle cx="9" cy="7" r="4"/>
          <path d="M23 21v-2a4 4 0 0 0-3-3.87"/>
          <path d="M16 3.13a4 4 0 0 1 0 7.75"/>
        </svg>
        Customer Management
      </a>
      <div class="nav-divider"></div>
      <ul class="navbar-nav">
        <li><a routerLink="/customers" routerLinkActive="active">Details</a></li>
        <li><a routerLink="/names" routerLinkActive="active">Names</a></li>
        <li><a routerLink="/addresses" routerLinkActive="active">Addresses</a></li>
        <li><a routerLink="/contacts" routerLinkActive="active">Contacts</a></li>
        <li><a routerLink="/identification" routerLinkActive="active">Identification</a></li>
        <li><a routerLink="/classification" routerLinkActive="active">Classification</a></li>
        <li><a routerLink="/proof-of-identity" routerLinkActive="active">Proof of Identity</a></li>
      </ul>
    </nav>
  `
})
export class NavbarComponent {}
