import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../services/api.service';
import { CustomerProofOfIdentityRequest, CustomerProofOfIdentityResponse } from '../../models/models';

@Component({
  selector: 'app-customer-proof',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="page">
      <div class="page-header">
        <div>
          <div class="page-title">Customer Proof of Identity</div>
          <div class="page-subtitle">Manage proof of identity document records</div>
        </div>
        <button class="btn btn-primary" (click)="openModal()">+ Add Proof</button>
      </div>

      <div *ngIf="successMsg" class="alert alert-success">✓ {{ successMsg }}</div>
      <div *ngIf="errorMsg" class="alert alert-danger">✗ {{ errorMsg }}</div>

      <div class="card">
        <div *ngIf="loading" class="spinner"><div class="spinner-ring"></div></div>
        <div *ngIf="!loading">
          <div *ngIf="items.length === 0" class="empty-state"><p>No proof of identity records found.</p></div>
          <div *ngIf="items.length > 0" class="table-wrapper">
            <table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Customer ID</th>
                  <th>Classification Value</th>
                  <th>Start Date</th>
                  <th>End Date</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                <tr *ngFor="let item of items">
                  <td>{{ item.id }}</td>
                  <td>{{ item.customerIdentifier }}</td>
                  <td><span class="badge badge-info">{{ item.customerClassificationTypeValue }}</span></td>
                  <td>{{ item.startDate }}</td>
                  <td>{{ item.endDate }}</td>
                  <td>
                    <div class="td-actions">
                      <button class="btn btn-outline btn-sm" (click)="openModal(item)">Edit</button>
                      <button class="btn btn-danger btn-sm" (click)="delete(item.id)">Delete</button>
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
          <span class="modal-title">{{ editId ? 'Edit Proof of Identity' : 'Add Proof of Identity' }}</span>
          <button class="modal-close" (click)="closeModal()">×</button>
        </div>
        <div class="modal-body">
          <div class="section-label">Proof of Identity Details</div>
          <div class="form-grid">
            <div class="form-group">
              <label>Customer Identifier *</label>
              <input type="number" [(ngModel)]="form.customerIdentifier" placeholder="Customer ID" required />
            </div>
            <div class="form-group">
              <label>Classification Type Value *</label>
              <input type="text" [(ngModel)]="form.customerClassificationTypeValue" placeholder="e.g. PASSPORT, NATIONAL_ID" required />
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
export class CustomerProofComponent implements OnInit {
  items: CustomerProofOfIdentityResponse[] = [];
  loading = false; saving = false; showModal = false;
  editId: number | null = null;
  successMsg = ''; errorMsg = '';
  form: CustomerProofOfIdentityRequest = this.blank();

  constructor(private api: ApiService) {}
  ngOnInit() { this.load(); }

  load() {
    this.loading = true;
    this.api.getProofs().subscribe({
      next: d => { this.items = d; this.loading = false; },
      error: () => { this.errorMsg = 'Failed to load'; this.loading = false; }
    });
  }

  blank(): CustomerProofOfIdentityRequest {
    return { customerIdentifier: 0, customerClassificationTypeValue: '',
      startDate: '', endDate: '', crudValue: '',
      userID: '', workstationID: '', programID: '', uuid: '' };
  }

  openModal(item?: CustomerProofOfIdentityResponse) {
    this.successMsg = ''; this.errorMsg = '';
    if (item) {
      this.editId = item.id;
      this.form = { customerIdentifier: item.customerIdentifier,
        customerClassificationTypeValue: item.customerClassificationTypeValue,
        startDate: item.startDate, endDate: item.endDate, crudValue: item.crudValue,
        userID: item.userID, workstationID: item.workstationID, programID: item.programID, uuid: item.uuid };
    } else { this.editId = null; this.form = this.blank(); }
    this.showModal = true;
  }

  closeModal() { this.showModal = false; }

  save() {
    this.saving = true;
    const obs = this.editId
      ? this.api.updateProof(this.editId, this.form)
      : this.api.createProof(this.form);
    obs.subscribe({
      next: () => { this.saving = false; this.showModal = false; this.successMsg = this.editId ? 'Updated!' : 'Created!'; this.load(); },
      error: () => { this.saving = false; this.errorMsg = 'Save failed.'; }
    });
  }

  delete(id: number) {
    if (!confirm('Delete this record?')) return;
    this.api.deleteProof(id).subscribe({
      next: () => { this.successMsg = 'Deleted.'; this.load(); },
      error: () => { this.errorMsg = 'Delete failed.'; }
    });
  }
}
