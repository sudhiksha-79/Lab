import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterLink],
  template: `
    <div class="page">
      <div style="text-align:center; padding: 48px 0 32px;">
        <svg viewBox="0 0 48 48" fill="none" xmlns="http://www.w3.org/2000/svg"
          style="width:64px;height:64px;margin-bottom:16px;color:var(--primary)">
          <circle cx="18" cy="14" r="8" stroke="currentColor" stroke-width="2.5"/>
          <path d="M2 42c0-8.837 7.163-16 16-16s16 7.163 16 16" stroke="currentColor" stroke-width="2.5" stroke-linecap="round"/>
          <path d="M32 6a8 8 0 010 16" stroke="currentColor" stroke-width="2.5" stroke-linecap="round"/>
          <path d="M46 42c0-8.837-7.163-16-16-16" stroke="currentColor" stroke-width="2.5" stroke-linecap="round"/>
        </svg>
        <h1 style="font-size:28px;font-weight:700;color:var(--text);margin-bottom:8px;">Customer Management System</h1>
        <p style="color:var(--text-muted);font-size:15px;max-width:500px;margin:0 auto;">
          Manage all customer data across 7 modules. Use the navigation bar to switch between modules.
        </p>
      </div>

      <div style="display:grid;grid-template-columns:repeat(auto-fill,minmax(280px,1fr));gap:20px;margin-top:8px;">

        <a routerLink="/customers" class="card" style="text-decoration:none;transition:box-shadow .15s;cursor:pointer;"
          onmouseover="this.style.boxShadow='0 4px 20px rgba(0,0,0,.14)'"
          onmouseout="this.style.boxShadow='0 2px 8px rgba(0,0,0,.08)'">
          <div class="card-body" style="display:flex;gap:16px;align-items:flex-start;">
            <div style="background:var(--primary-light);border-radius:10px;padding:12px;flex-shrink:0;">
              <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="var(--primary)" stroke-width="2">
                <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/><circle cx="12" cy="7" r="4"/>
              </svg>
            </div>
            <div>
              <div style="font-weight:700;font-size:15px;color:var(--text);margin-bottom:4px;">Customer Details</div>
              <div style="font-size:13px;color:var(--text-muted);">Core customer records including type, DOB, status, gender, language and country.</div>
            </div>
          </div>
        </a>

        <a routerLink="/names" class="card" style="text-decoration:none;transition:box-shadow .15s;cursor:pointer;"
          onmouseover="this.style.boxShadow='0 4px 20px rgba(0,0,0,.14)'"
          onmouseout="this.style.boxShadow='0 2px 8px rgba(0,0,0,.08)'">
          <div class="card-body" style="display:flex;gap:16px;align-items:flex-start;">
            <div style="background:#e8f5e9;border-radius:10px;padding:12px;flex-shrink:0;">
              <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="#2e7d32" stroke-width="2">
                <path d="M12 20h9"/><path d="M16.5 3.5a2.121 2.121 0 0 1 3 3L7 19l-4 1 1-4L16.5 3.5z"/>
              </svg>
            </div>
            <div>
              <div style="font-weight:700;font-size:15px;color:var(--text);margin-bottom:4px;">Customer Names</div>
              <div style="font-size:13px;color:var(--text-muted);">Name components like first, middle, last, prefix and suffix.</div>
            </div>
          </div>
        </a>

        <a routerLink="/addresses" class="card" style="text-decoration:none;transition:box-shadow .15s;cursor:pointer;"
          onmouseover="this.style.boxShadow='0 4px 20px rgba(0,0,0,.14)'"
          onmouseout="this.style.boxShadow='0 2px 8px rgba(0,0,0,.08)'">
          <div class="card-body" style="display:flex;gap:16px;align-items:flex-start;">
            <div style="background:#fff3e0;border-radius:10px;padding:12px;flex-shrink:0;">
              <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="#f57c00" stroke-width="2">
                <path d="M21 10c0 7-9 13-9 13s-9-6-9-13a9 9 0 0 1 18 0z"/><circle cx="12" cy="10" r="3"/>
              </svg>
            </div>
            <div>
              <div style="font-weight:700;font-size:15px;color:var(--text);margin-bottom:4px;">Customer Addresses</div>
              <div style="font-size:13px;color:var(--text-muted);">Address components — street, city, state, ZIP and country.</div>
            </div>
          </div>
        </a>

        <a routerLink="/contacts" class="card" style="text-decoration:none;transition:box-shadow .15s;cursor:pointer;"
          onmouseover="this.style.boxShadow='0 4px 20px rgba(0,0,0,.14)'"
          onmouseout="this.style.boxShadow='0 2px 8px rgba(0,0,0,.08)'">
          <div class="card-body" style="display:flex;gap:16px;align-items:flex-start;">
            <div style="background:#fce4ec;border-radius:10px;padding:12px;flex-shrink:0;">
              <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="#c2185b" stroke-width="2">
                <path d="M22 16.92v3a2 2 0 0 1-2.18 2 19.79 19.79 0 0 1-8.63-3.07A19.5 19.5 0 0 1 4.69 12 19.79 19.79 0 0 1 1.61 3.4 2 2 0 0 1 3.6 1.21h3a2 2 0 0 1 2 1.72 12.84 12.84 0 0 0 .7 2.81 2 2 0 0 1-.45 2.11L7.91 8.91a16 16 0 0 0 6.18 6.18l.91-.91a2 2 0 0 1 2.11-.45 12.84 12.84 0 0 0 2.81.7A2 2 0 0 1 22 16.92z"/>
              </svg>
            </div>
            <div>
              <div style="font-weight:700;font-size:15px;color:var(--text);margin-bottom:4px;">Contact Details</div>
              <div style="font-size:13px;color:var(--text-muted);">Phone numbers, email addresses and other contact information.</div>
            </div>
          </div>
        </a>

        <a routerLink="/identification" class="card" style="text-decoration:none;transition:box-shadow .15s;cursor:pointer;"
          onmouseover="this.style.boxShadow='0 4px 20px rgba(0,0,0,.14)'"
          onmouseout="this.style.boxShadow='0 2px 8px rgba(0,0,0,.08)'">
          <div class="card-body" style="display:flex;gap:16px;align-items:flex-start;">
            <div style="background:#e8eaf6;border-radius:10px;padding:12px;flex-shrink:0;">
              <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="#3949ab" stroke-width="2">
                <rect x="2" y="5" width="20" height="14" rx="2"/><path d="M2 10h20"/>
              </svg>
            </div>
            <div>
              <div style="font-weight:700;font-size:15px;color:var(--text);margin-bottom:4px;">Identification</div>
              <div style="font-size:13px;color:var(--text-muted);">Passport, Aadhar, PAN, driving licence and other ID documents.</div>
            </div>
          </div>
        </a>

        <a routerLink="/classification" class="card" style="text-decoration:none;transition:box-shadow .15s;cursor:pointer;"
          onmouseover="this.style.boxShadow='0 4px 20px rgba(0,0,0,.14)'"
          onmouseout="this.style.boxShadow='0 2px 8px rgba(0,0,0,.08)'">
          <div class="card-body" style="display:flex;gap:16px;align-items:flex-start;">
            <div style="background:#e0f7fa;border-radius:10px;padding:12px;flex-shrink:0;">
              <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="#0097a7" stroke-width="2">
                <path d="M4 6h16M4 10h16M4 14h16M4 18h7"/>
              </svg>
            </div>
            <div>
              <div style="font-weight:700;font-size:15px;color:var(--text);margin-bottom:4px;">Classification</div>
              <div style="font-size:13px;color:var(--text-muted);">Customer segments, tiers, risk levels and priority categories.</div>
            </div>
          </div>
        </a>

        <a routerLink="/proof-of-identity" class="card" style="text-decoration:none;transition:box-shadow .15s;cursor:pointer;"
          onmouseover="this.style.boxShadow='0 4px 20px rgba(0,0,0,.14)'"
          onmouseout="this.style.boxShadow='0 2px 8px rgba(0,0,0,.08)'">
          <div class="card-body" style="display:flex;gap:16px;align-items:flex-start;">
            <div style="background:#f3e5f5;border-radius:10px;padding:12px;flex-shrink:0;">
              <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="#7b1fa2" stroke-width="2">
                <path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"/>
                <polyline points="14 2 14 8 20 8"/>
                <line x1="16" y1="13" x2="8" y2="13"/>
                <line x1="16" y1="17" x2="8" y2="17"/>
                <polyline points="10 9 9 9 8 9"/>
              </svg>
            </div>
            <div>
              <div style="font-weight:700;font-size:15px;color:var(--text);margin-bottom:4px;">Proof of Identity</div>
              <div style="font-size:13px;color:var(--text-muted);">Identity document verification and validity periods.</div>
            </div>
          </div>
        </a>

      </div>
    </div>
  `
})
export class HomeComponent {}
