import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../services/api.service';
import { CustomerAddressRequest, CustomerAddressResponse } from '../../models/models';

@Component({
  selector: 'app-customer-address',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="page">
      <div class="page-header">
        <div>
          <div class="page-title">Customer Addresses</div>
          <div class="page-subtitle">Manage customer address records</div>
        </div>
        <button class="btn btn-primary" (click)="openModal()">+ Add Address</button>
      </div>

      <div *ngIf="successMsg" class="alert alert-success">✓ {{ successMsg }}</div>
      <div *ngIf="errorMsg" class="alert alert-danger">✗ {{ errorMsg }}</div>

      <div class="card">
        <div *ngIf="loading" class="spinner"><div class="spinner-ring"></div></div>
        <div *ngIf="!loading">
          <div *ngIf="addresses.length === 0" class="empty-state"><p>No address records found.</p></div>
          <div *ngIf="addresses.length > 0" class="table-wrapper">
            <table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Customer ID</th>
                  <th>Component Type</th>
                  <th>Component Value</th>
                  <th>Effective Date</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                <tr *ngFor="let a of addresses">
                  <td>{{ a.id }}</td>
                  <td>{{ a.customerIdentifier }}</td>
                  <td><span class="badge badge-info">{{ a.customerNameComponentType }}</span></td>
                  <td>{{ a.customerNameComponentValue }}</td>
                  <td>{{ a.effectiveDate }}</td>
                  <td>
                    <div class="td-actions">
                      <button class="btn btn-outline btn-sm" (click)="openModal(a)">Edit</button>
                      <button class="btn btn-danger btn-sm" (click)="delete(a.id)">Delete</button>
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
          <span class="modal-title">{{ editId ? 'Edit Address' : 'Add Address' }}</span>
          <button class="modal-close" (click)="closeModal()">×</button>
        </div>
        <div class="modal-body">
          <div class="section-label">Address Details</div>
          <div class="form-grid">
            <div class="form-group">
              <label>Customer Identifier *</label>
              <input type="number" [(ngModel)]="form.customerIdentifier" placeholder="Customer ID" required />
            </div>
            <div class="form-group">
              <label>Address Component Type *</label>
              <select [(ngModel)]="form.customerNameComponentType">
                <option value="">-- Select --</option>
                <option value="STREET">STREET</option>
                <option value="CITY">CITY</option>
                <option value="STATE">STATE</option>
                <option value="ZIP">ZIP</option>
                <option value="COUNTRY">COUNTRY</option>
                <option value="LANDMARK">LANDMARK</option>
              </select>
            </div>
            <div class="form-group">
              <label>Address Component Value *</label>
              <input type="text" [(ngModel)]="form.customerNameComponentValue" placeholder="e.g. 123 Main St" required />
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
export class CustomerAddressComponent implements OnInit {
  addresses: CustomerAddressResponse[] = [];
  loading = false; saving = false; showModal = false;
  editId: number | null = null;
  successMsg = ''; errorMsg = '';
  form: CustomerAddressRequest = this.blank();

  constructor(private api: ApiService) {}
  ngOnInit() { this.load(); }

  load() {
    this.loading = true;
    this.api.getAddresses().subscribe({
      next: d => { this.addresses = d; this.loading = false; },
      error: () => { this.errorMsg = 'Failed to load'; this.loading = false; }
    });
  }

  blank(): CustomerAddressRequest {
    return { customerIdentifier: 0, customerNameComponentType: '', customerNameComponentValue: '',
      effectiveDate: '', crudValue: '', userID: '', workstationID: '', programID: '', uuid: '' };
  }

  openModal(a?: CustomerAddressResponse) {
    this.successMsg = ''; this.errorMsg = '';
    if (a) {
      this.editId = a.id;
      this.form = { customerIdentifier: a.customerIdentifier,
        customerNameComponentType: a.customerNameComponentType,
        customerNameComponentValue: a.customerNameComponentValue,
        effectiveDate: a.effectiveDate, crudValue: a.crudValue,
        userID: a.userID, workstationID: a.workstationID, programID: a.programID, uuid: a.uuid };
    } else { this.editId = null; this.form = this.blank(); }
    this.showModal = true;
  }

  closeModal() { this.showModal = false; }

  save() {
    this.saving = true;
    const obs = this.editId ? this.api.updateAddress(this.editId, this.form) : this.api.createAddress(this.form);
    obs.subscribe({
      next: () => { this.saving = false; this.showModal = false; this.successMsg = this.editId ? 'Updated!' : 'Created!'; this.load(); },
      error: () => { this.saving = false; this.errorMsg = 'Save failed.'; }
    });
  }

  delete(id: number) {
    if (!confirm('Delete this address?')) return;
    this.api.deleteAddress(id).subscribe({
      next: () => { this.successMsg = 'Deleted.'; this.load(); },
      error: () => { this.errorMsg = 'Delete failed.'; }
    });
  }
}
