import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../services/api.service';
import { CustomerDetailsRequest, CustomerDetailsResponse } from '../../models/models';

@Component({
  selector: 'app-customer-details',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="page">
      <div class="page-header">
        <div>
          <div class="page-title">Customer Details</div>
          <div class="page-subtitle">Manage core customer records</div>
        </div>
        <button class="btn btn-primary" (click)="openModal()">
          + Add Customer
        </button>
      </div>

      <div *ngIf="successMsg" class="alert alert-success">✓ {{ successMsg }}</div>
      <div *ngIf="errorMsg" class="alert alert-danger">✗ {{ errorMsg }}</div>

      <div class="card">
        <div *ngIf="loading" class="spinner"><div class="spinner-ring"></div></div>
        <div *ngIf="!loading">
          <div *ngIf="customers.length === 0" class="empty-state">
            <p>No customers found. Click "Add Customer" to get started.</p>
          </div>
          <div *ngIf="customers.length > 0" class="table-wrapper">
            <table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Type</th>
                  <th>DOB</th>
                  <th>Status</th>
                  <th>Gender</th>
                  <th>Language</th>
                  <th>Country</th>
                  <th>Effective Date</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                <tr *ngFor="let c of customers">
                  <td>{{ c.customerIdentifier }}</td>
                  <td><span class="badge badge-info">{{ c.customerType }}</span></td>
                  <td>{{ c.customerDOB }}</td>
                  <td><span class="badge badge-success">{{ c.customerStatus }}</span></td>
                  <td>{{ c.customerGender }}</td>
                  <td>{{ c.customerPreferredLanguage }}</td>
                  <td>{{ c.customerCountryOrigin }}</td>
                  <td>{{ c.effectiveDate }}</td>
                  <td>
                    <div class="td-actions">
                      <button class="btn btn-outline btn-sm" (click)="openModal(c)">Edit</button>
                      <button class="btn btn-danger btn-sm" (click)="delete(c.customerIdentifier)">Delete</button>
                    </div>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

    <!-- Modal -->
    <div class="modal-overlay" *ngIf="showModal" (click)="closeModal()">
      <div class="modal" (click)="$event.stopPropagation()">
        <div class="modal-header">
          <span class="modal-title">{{ editId ? 'Edit Customer' : 'Add Customer' }}</span>
          <button class="modal-close" (click)="closeModal()">×</button>
        </div>
        <div class="modal-body">
          <div class="section-label">Customer Information</div>
          <div class="form-grid">
            <div class="form-group">
              <label>Customer Type *</label>
              <input type="text" [(ngModel)]="form.customerType" placeholder="e.g. INDIVIDUAL" required />
            </div>
            <div class="form-group">
              <label>Date of Birth *</label>
              <input type="date" [(ngModel)]="form.customerDOB" required />
            </div>
            <div class="form-group">
              <label>Status *</label>
              <select [(ngModel)]="form.customerStatus">
                <option value="">-- Select --</option>
                <option value="ACTIVE">ACTIVE</option>
                <option value="INACTIVE">INACTIVE</option>
                <option value="PENDING">PENDING</option>
                <option value="SUSPENDED">SUSPENDED</option>
              </select>
            </div>
            <div class="form-group">
              <label>Gender *</label>
              <select [(ngModel)]="form.customerGender">
                <option value="">-- Select --</option>
                <option value="MALE">MALE</option>
                <option value="FEMALE">FEMALE</option>
                <option value="OTHER">OTHER</option>
              </select>
            </div>
            <div class="form-group">
              <label>Preferred Language</label>
              <input type="text" [(ngModel)]="form.customerPreferredLanguage" placeholder="e.g. English" />
            </div>
            <div class="form-group">
              <label>Country of Origin</label>
              <input type="text" [(ngModel)]="form.customerCountryOrigin" placeholder="e.g. India" />
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
export class CustomerDetailsComponent implements OnInit {
  customers: CustomerDetailsResponse[] = [];
  loading = false;
  saving = false;
  showModal = false;
  editId: number | null = null;
  successMsg = '';
  errorMsg = '';

  form: CustomerDetailsRequest = this.blank();

  constructor(private api: ApiService) {}

  ngOnInit() { this.load(); }

  load() {
    this.loading = true;
    this.api.getCustomers().subscribe({
      next: d => { this.customers = d; this.loading = false; },
      error: () => { this.errorMsg = 'Failed to load customers'; this.loading = false; }
    });
  }

  blank(): CustomerDetailsRequest {
    return { customerType:'', customerDOB:'', customerStatus:'', customerGender:'',
      customerPreferredLanguage:'', customerCountryOrigin:'', effectiveDate:'',
      userID:'', workstationID:'', programID:'', uuid:'' };
  }

  openModal(c?: CustomerDetailsResponse) {
    this.successMsg = ''; this.errorMsg = '';
    if (c) {
      this.editId = c.customerIdentifier;
      this.form = {
        customerType: c.customerType, customerDOB: c.customerDOB,
        customerStatus: c.customerStatus, customerGender: c.customerGender,
        customerPreferredLanguage: c.customerPreferredLanguage,
        customerCountryOrigin: c.customerCountryOrigin,
        effectiveDate: c.effectiveDate, userID: c.userID,
        workstationID: c.workstationID, programID: c.programID, uuid: c.uuid
      };
    } else {
      this.editId = null;
      this.form = this.blank();
    }
    this.showModal = true;
  }

  closeModal() { this.showModal = false; }

  save() {
    this.saving = true;
    const obs = this.editId
      ? this.api.updateCustomer(this.editId, this.form)
      : this.api.createCustomer(this.form);
    obs.subscribe({
      next: () => {
        this.saving = false; this.showModal = false;
        this.successMsg = this.editId ? 'Customer updated!' : 'Customer created!';
        this.load();
      },
      error: () => { this.saving = false; this.errorMsg = 'Save failed. Check your input.'; }
    });
  }

  delete(id: number) {
    if (!confirm('Delete this customer?')) return;
    this.api.deleteCustomer(id).subscribe({
      next: () => { this.successMsg = 'Customer deleted.'; this.load(); },
      error: () => { this.errorMsg = 'Delete failed.'; }
    });
  }
}
