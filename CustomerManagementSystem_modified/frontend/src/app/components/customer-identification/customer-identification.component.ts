import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../services/api.service';
import { CustomerIdentificationRequest, CustomerIdentificationResponse } from '../../models/models';

@Component({
  selector: 'app-customer-identification',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="page">
      <div class="page-header">
        <div>
          <div class="page-title">Customer Identification</div>
          <div class="page-subtitle">Manage customer identification records</div>
        </div>
        <button class="btn btn-primary" (click)="openModal()">+ Add Identification</button>
      </div>

      <div *ngIf="successMsg" class="alert alert-success">✓ {{ successMsg }}</div>
      <div *ngIf="errorMsg" class="alert alert-danger">✗ {{ errorMsg }}</div>

      <div class="card">
        <div *ngIf="loading" class="spinner"><div class="spinner-ring"></div></div>
        <div *ngIf="!loading">
          <div *ngIf="items.length === 0" class="empty-state"><p>No identification records found.</p></div>
          <div *ngIf="items.length > 0" class="table-wrapper">
            <table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Identification Type</th>
                  <th>Identification Item</th>
                  <th>Effective Date</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                <tr *ngFor="let item of items">
                  <td>{{ item.customerIdentificationID }}</td>
                  <td><span class="badge badge-info">{{ item.customerIdentificationType }}</span></td>
                  <td>{{ item.customerIdentificationItem }}</td>
                  <td>{{ item.effectiveDate }}</td>
                  <td>
                    <div class="td-actions">
                      <button class="btn btn-outline btn-sm" (click)="openModal(item)">Edit</button>
                      <button class="btn btn-danger btn-sm" (click)="delete(item.customerIdentificationID)">Delete</button>
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
          <span class="modal-title">{{ editId ? 'Edit Identification' : 'Add Identification' }}</span>
          <button class="modal-close" (click)="closeModal()">×</button>
        </div>
        <div class="modal-body">
          <div class="section-label">Identification Details</div>
          <div class="form-grid">
            <div class="form-group">
              <label>Identification Type *</label>
              <select [(ngModel)]="form.customerIdentificationType">
                <option value="">-- Select --</option>
                <option value="PASSPORT">PASSPORT</option>
                <option value="NATIONAL_ID">NATIONAL ID</option>
                <option value="DRIVING_LICENSE">DRIVING LICENSE</option>
                <option value="PAN">PAN</option>
                <option value="AADHAR">AADHAR</option>
                <option value="VOTER_ID">VOTER ID</option>
                <option value="SSN">SSN</option>
                <option value="OTHER">OTHER</option>
              </select>
            </div>
            <div class="form-group">
              <label>Identification Item *</label>
              <input type="text" [(ngModel)]="form.customerIdentificationItem" placeholder="e.g. A1234567" required />
            </div>
            <div class="form-group">
              <label>Effective Date *</label>
              <input type="date" [(ngModel)]="form.effectiveDate" required />
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
export class CustomerIdentificationComponent implements OnInit {
  items: CustomerIdentificationResponse[] = [];
  loading = false; saving = false; showModal = false;
  editId: number | null = null;
  successMsg = ''; errorMsg = '';
  form: CustomerIdentificationRequest = this.blank();

  constructor(private api: ApiService) {}
  ngOnInit() { this.load(); }

  load() {
    this.loading = true;
    this.api.getIdentifications().subscribe({
      next: d => { this.items = d; this.loading = false; },
      error: () => { this.errorMsg = 'Failed to load'; this.loading = false; }
    });
  }

  blank(): CustomerIdentificationRequest {
    return { customerIdentificationType: '', customerIdentificationItem: '',
      effectiveDate: '', crudValue: '', userID: '', workstationID: '', programID: '', uuid: '' };
  }

  openModal(item?: CustomerIdentificationResponse) {
    this.successMsg = ''; this.errorMsg = '';
    if (item) {
      this.editId = item.customerIdentificationID;
      this.form = { customerIdentificationType: item.customerIdentificationType,
        customerIdentificationItem: item.customerIdentificationItem,
        effectiveDate: item.effectiveDate, crudValue: item.crudValue,
        userID: item.userID, workstationID: item.workstationID, programID: item.programID, uuid: item.uuid };
    } else { this.editId = null; this.form = this.blank(); }
    this.showModal = true;
  }

  closeModal() { this.showModal = false; }

  save() {
    this.saving = true;
    const obs = this.editId
      ? this.api.updateIdentification(this.editId, this.form)
      : this.api.createIdentification(this.form);
    obs.subscribe({
      next: () => { this.saving = false; this.showModal = false; this.successMsg = this.editId ? 'Updated!' : 'Created!'; this.load(); },
      error: () => { this.saving = false; this.errorMsg = 'Save failed.'; }
    });
  }

  delete(id: number) {
    if (!confirm('Delete this record?')) return;
    this.api.deleteIdentification(id).subscribe({
      next: () => { this.successMsg = 'Deleted.'; this.load(); },
      error: () => { this.errorMsg = 'Delete failed.'; }
    });
  }
}
