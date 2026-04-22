import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../services/api.service';
import { CustomerClassificationRequest, CustomerClassificationResponse } from '../../models/models';

@Component({
  selector: 'app-customer-classification',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="page">
      <div class="page-header">
        <div>
          <div class="page-title">Customer Classification</div>
          <div class="page-subtitle">Manage customer classification records</div>
        </div>
        <button class="btn btn-primary" (click)="openModal()">+ Add Classification</button>
      </div>

      <div *ngIf="successMsg" class="alert alert-success">✓ {{ successMsg }}</div>
      <div *ngIf="errorMsg" class="alert alert-danger">✗ {{ errorMsg }}</div>

      <div class="card">
        <div *ngIf="loading" class="spinner"><div class="spinner-ring"></div></div>
        <div *ngIf="!loading">
          <div *ngIf="items.length === 0" class="empty-state"><p>No classification records found.</p></div>
          <div *ngIf="items.length > 0" class="table-wrapper">
            <table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Classification Type</th>
                  <th>Classification Value</th>
                  <th>Effective Date</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                <tr *ngFor="let item of items">
                  <td>{{ item.id }}</td>
                  <td><span class="badge badge-info">{{ item.customerClassificationType }}</span></td>
                  <td>{{ item.customerClassificationTypeValue }}</td>
                  <td>{{ item.effectiveDate }}</td>
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
          <span class="modal-title">{{ editId ? 'Edit Classification' : 'Add Classification' }}</span>
          <button class="modal-close" (click)="closeModal()">×</button>
        </div>
        <div class="modal-body">
          <div class="section-label">Classification Details</div>
          <div class="form-grid">
            <div class="form-group">
              <label>Classification Type *</label>
              <select [(ngModel)]="form.customerClassificationType">
                <option value="">-- Select --</option>
                <option value="SEGMENT">SEGMENT</option>
                <option value="TIER">TIER</option>
                <option value="RISK">RISK</option>
                <option value="CATEGORY">CATEGORY</option>
                <option value="PRIORITY">PRIORITY</option>
                <option value="OTHER">OTHER</option>
              </select>
            </div>
            <div class="form-group">
              <label>Classification Type Value *</label>
              <input type="text" [(ngModel)]="form.customerClassificationTypeValue" placeholder="e.g. PREMIUM" required />
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
export class CustomerClassificationComponent implements OnInit {
  items: CustomerClassificationResponse[] = [];
  loading = false; saving = false; showModal = false;
  editId: number | null = null;
  successMsg = ''; errorMsg = '';
  form: CustomerClassificationRequest = this.blank();

  constructor(private api: ApiService) {}
  ngOnInit() { this.load(); }

  load() {
    this.loading = true;
    this.api.getClassifications().subscribe({
      next: d => { this.items = d; this.loading = false; },
      error: () => { this.errorMsg = 'Failed to load'; this.loading = false; }
    });
  }

  blank(): CustomerClassificationRequest {
    return { customerClassificationType: '', customerClassificationTypeValue: '',
      effectiveDate: '', crudValue: '', userID: '', workstationID: '', programID: '', uuid: '' };
  }

  openModal(item?: CustomerClassificationResponse) {
    this.successMsg = ''; this.errorMsg = '';
    if (item) {
      this.editId = item.id;
      this.form = { customerClassificationType: item.customerClassificationType,
        customerClassificationTypeValue: item.customerClassificationTypeValue,
        effectiveDate: item.effectiveDate, crudValue: item.crudValue,
        userID: item.userID, workstationID: item.workstationID, programID: item.programID, uuid: item.uuid };
    } else { this.editId = null; this.form = this.blank(); }
    this.showModal = true;
  }

  closeModal() { this.showModal = false; }

  save() {
    this.saving = true;
    const obs = this.editId
      ? this.api.updateClassification(this.editId, this.form)
      : this.api.createClassification(this.form);
    obs.subscribe({
      next: () => { this.saving = false; this.showModal = false; this.successMsg = this.editId ? 'Updated!' : 'Created!'; this.load(); },
      error: () => { this.saving = false; this.errorMsg = 'Save failed.'; }
    });
  }

  delete(id: number) {
    if (!confirm('Delete this record?')) return;
    this.api.deleteClassification(id).subscribe({
      next: () => { this.successMsg = 'Deleted.'; this.load(); },
      error: () => { this.errorMsg = 'Delete failed.'; }
    });
  }
}
