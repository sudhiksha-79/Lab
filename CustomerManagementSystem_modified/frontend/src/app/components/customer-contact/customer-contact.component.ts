import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../services/api.service';
import { CustomerContactDetailsRequest, CustomerContactDetailsResponse } from '../../models/models';

@Component({
  selector: 'app-customer-contact',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="page">
      <div class="page-header">
        <div>
          <div class="page-title">Customer Contact Details</div>
          <div class="page-subtitle">Manage customer contact information</div>
        </div>
        <button class="btn btn-primary" (click)="openModal()">+ Add Contact</button>
      </div>

      <div *ngIf="successMsg" class="alert alert-success">✓ {{ successMsg }}</div>
      <div *ngIf="errorMsg" class="alert alert-danger">✗ {{ errorMsg }}</div>

      <div class="card">
        <div *ngIf="loading" class="spinner"><div class="spinner-ring"></div></div>
        <div *ngIf="!loading">
          <div *ngIf="contacts.length === 0" class="empty-state"><p>No contact records found.</p></div>
          <div *ngIf="contacts.length > 0" class="table-wrapper">
            <table>
              <thead>
                <tr>
                  <th>Contact ID</th>
                  <th>Customer ID</th>
                  <th>Contact Details</th>
                  <th>Start Date</th>
                  <th>End Date</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                <tr *ngFor="let c of contacts">
                  <td>{{ c.customerContactID }}</td>
                  <td>{{ c.customerId }}</td>
                  <td>{{ c.customerContactDetails }}</td>
                  <td>{{ c.startDate }}</td>
                  <td>{{ c.endDate }}</td>
                  <td>
                    <div class="td-actions">
                      <button class="btn btn-outline btn-sm" (click)="openModal(c)">Edit</button>
                      <button class="btn btn-danger btn-sm" (click)="delete(c.customerContactID)">Delete</button>
                    </div>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

    <div class="modal-overlay" *ngIf="showModal" (click)="closeModal()">
      <div class="modal" (click)="$event.stopPropagation()">
        <div class="modal-header">
          <span class="modal-title">{{ editId ? 'Edit Contact' : 'Add Contact' }}</span>
          <button class="modal-close" (click)="closeModal()">×</button>
        </div>
        <div class="modal-body">
          <div class="section-label">Contact Details</div>
          <div class="form-grid">
            <div class="form-group">
              <label>Customer ID *</label>
              <input type="number" [(ngModel)]="form.customerId" placeholder="Customer ID" required />
            </div>
            <div class="form-group">
              <label>Contact Details *</label>
              <input type="text" [(ngModel)]="form.customerContactDetails" placeholder="e.g. +91-9876543210 or email@example.com" required />
            </div>
            <div class="form-group">
              <label>Start Date *</label>
              <input type="date" [(ngModel)]="form.startDate" required />
            </div>
            <div class="form-group">
              <label>End Date</label>
              <input type="date" [(ngModel)]="form.endDate" />
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button class="btn btn-ghost" (click)="closeModal()">Cancel</button>
          <button class="btn btn-primary" (click)="save()" [disabled]="saving">
            {{ saving ? 'Saving...' : (editId ? 'Update' : 'Create') }}
          </button>
        </div>
      </div>
    </div>
  `
})
export class CustomerContactComponent implements OnInit {
  contacts: CustomerContactDetailsResponse[] = [];
  loading = false; saving = false; showModal = false;
  editId: number | null = null;
  successMsg = ''; errorMsg = '';
  form: CustomerContactDetailsRequest = this.blank();

  constructor(private api: ApiService) {}
  ngOnInit() { this.load(); }

  load() {
    this.loading = true;
    this.api.getContacts().subscribe({
      next: d => { this.contacts = d; this.loading = false; },
      error: () => { this.errorMsg = 'Failed to load'; this.loading = false; }
    });
  }

  blank(): CustomerContactDetailsRequest {
    return { customerId: 0, customerContactDetails: '', startDate: '', endDate: '',
      crudValue: '', userID: '', workstationID: '', programID: '', uuid: '' };
  }

  openModal(c?: CustomerContactDetailsResponse) {
    this.successMsg = ''; this.errorMsg = '';
    if (c) {
      this.editId = c.customerContactID;
      this.form = { customerId: c.customerId, customerContactDetails: c.customerContactDetails,
        startDate: c.startDate, endDate: c.endDate, crudValue: c.crudValue,
        userID: c.userID, workstationID: c.workstationID, programID: c.programID, uuid: c.uuid };
    } else { this.editId = null; this.form = this.blank(); }
    this.showModal = true;
  }

  closeModal() { this.showModal = false; }

  save() {
    this.saving = true;
    const obs = this.editId
      ? this.api.updateContact(this.editId, this.form)
      : this.api.createContact(this.form);
    obs.subscribe({
      next: () => { this.saving = false; this.showModal = false; this.successMsg = this.editId ? 'Updated!' : 'Created!'; this.load(); },
      error: () => { this.saving = false; this.errorMsg = 'Save failed.'; }
    });
  }

  delete(id: number) {
    if (!confirm('Delete this contact?')) return;
    this.api.deleteContact(id).subscribe({
      next: () => { this.successMsg = 'Deleted.'; this.load(); },
      error: () => { this.errorMsg = 'Delete failed.'; }
    });
  }
}
