import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../services/api.service';
import { CustomerNameRequest, CustomerNameResponse } from '../../models/models';

@Component({
  selector: 'app-customer-name',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="page">
      <div class="page-header">
        <div>
          <div class="page-title">Customer Names</div>
          <div class="page-subtitle">Manage customer name components</div>
        </div>
        <button class="btn btn-primary" (click)="openModal()">+ Add Name</button>
      </div>

      <div *ngIf="successMsg" class="alert alert-success">✓ {{ successMsg }}</div>
      <div *ngIf="errorMsg" class="alert alert-danger">✗ {{ errorMsg }}</div>

      <div class="card">
        <div *ngIf="loading" class="spinner"><div class="spinner-ring"></div></div>
        <div *ngIf="!loading">
          <div *ngIf="names.length === 0" class="empty-state"><p>No name records found.</p></div>
          <div *ngIf="names.length > 0" class="table-wrapper">
            <table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Customer ID</th>
                  <th>Name Component Type</th>
                  <th>Name Component Value</th>
                  <th>Effective Date</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                <tr *ngFor="let n of names">
                  <td>{{ n.id }}</td>
                  <td>{{ n.customerIdentifier }}</td>
                  <td><span class="badge badge-info">{{ n.customerNameComponentType }}</span></td>
                  <td>{{ n.customerNameComponentValue }}</td>
                  <td>{{ n.effectiveDate }}</td>
                  <td>
                    <div class="td-actions">
                      <button class="btn btn-outline btn-sm" (click)="openModal(n)">Edit</button>
                      <button class="btn btn-danger btn-sm" (click)="delete(n.id)">Delete</button>
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
          <span class="modal-title">{{ editId ? 'Edit Name' : 'Add Name' }}</span>
          <button class="modal-close" (click)="closeModal()">×</button>
        </div>
        <div class="modal-body">
          <div class="section-label">Name Details</div>
          <div class="form-grid">
            <div class="form-group">
              <label>Customer Identifier *</label>
              <input type="number" [(ngModel)]="form.customerIdentifier" placeholder="Customer ID" required />
            </div>
            <div class="form-group">
              <label>Name Component Type *</label>
              <select [(ngModel)]="form.customerNameComponentType">
                <option value="">-- Select --</option>
                <option value="FIRST">FIRST</option>
                <option value="MIDDLE">MIDDLE</option>
                <option value="LAST">LAST</option>
                <option value="PREFIX">PREFIX</option>
                <option value="SUFFIX">SUFFIX</option>
                <option value="FULL">FULL</option>
              </select>
            </div>
            <div class="form-group">
              <label>Name Component Value *</label>
              <input type="text" [(ngModel)]="form.customerNameComponentValue" placeholder="e.g. John" required />
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
export class CustomerNameComponent implements OnInit {
  names: CustomerNameResponse[] = [];
  loading = false; saving = false; showModal = false;
  editId: number | null = null;
  successMsg = ''; errorMsg = '';
  form: CustomerNameRequest = this.blank();

  constructor(private api: ApiService) {}
  ngOnInit() { this.load(); }

  load() {
    this.loading = true;
    this.api.getNames().subscribe({
      next: d => { this.names = d; this.loading = false; },
      error: () => { this.errorMsg = 'Failed to load'; this.loading = false; }
    });
  }

  blank(): CustomerNameRequest {
    return { customerIdentifier: 0, customerNameComponentType: '', customerNameComponentValue: '',
      effectiveDate: '', crudValue: '', userID: '', workstationID: '', programID: '', uuid: '' };
  }

  openModal(n?: CustomerNameResponse) {
    this.successMsg = ''; this.errorMsg = '';
    if (n) {
      this.editId = n.id;
      this.form = { customerIdentifier: n.customerIdentifier,
        customerNameComponentType: n.customerNameComponentType,
        customerNameComponentValue: n.customerNameComponentValue,
        effectiveDate: n.effectiveDate, crudValue: n.crudValue,
        userID: n.userID, workstationID: n.workstationID, programID: n.programID, uuid: n.uuid };
    } else { this.editId = null; this.form = this.blank(); }
    this.showModal = true;
  }

  closeModal() { this.showModal = false; }

  save() {
    this.saving = true;
    const obs = this.editId ? this.api.updateName(this.editId, this.form) : this.api.createName(this.form);
    obs.subscribe({
      next: () => { this.saving = false; this.showModal = false; this.successMsg = this.editId ? 'Updated!' : 'Created!'; this.load(); },
      error: () => { this.saving = false; this.errorMsg = 'Save failed.'; }
    });
  }

  delete(id: number) {
    if (!confirm('Delete this record?')) return;
    this.api.deleteName(id).subscribe({
      next: () => { this.successMsg = 'Deleted.'; this.load(); },
      error: () => { this.errorMsg = 'Delete failed.'; }
    });
  }
}
