import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {
  CustomerDetailsRequest, CustomerDetailsResponse,
  CustomerNameRequest, CustomerNameResponse,
  CustomerAddressRequest, CustomerAddressResponse,
  CustomerContactDetailsRequest, CustomerContactDetailsResponse,
  CustomerIdentificationRequest, CustomerIdentificationResponse,
  CustomerClassificationRequest, CustomerClassificationResponse,
  CustomerProofOfIdentityRequest, CustomerProofOfIdentityResponse
} from '../models/models';

@Injectable({ providedIn: 'root' })
export class ApiService {
  private base = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  // ── Customer Details ──────────────────────────────────────────────────────
  getCustomers(): Observable<CustomerDetailsResponse[]> {
    return this.http.get<CustomerDetailsResponse[]>(`${this.base}/customer-details`);
  }
  getCustomer(id: number): Observable<CustomerDetailsResponse> {
    return this.http.get<CustomerDetailsResponse>(`${this.base}/customer-details/${id}`);
  }
  createCustomer(dto: CustomerDetailsRequest): Observable<CustomerDetailsResponse> {
    return this.http.post<CustomerDetailsResponse>(`${this.base}/customer-details`, dto);
  }
  updateCustomer(id: number, dto: CustomerDetailsRequest): Observable<CustomerDetailsResponse> {
    return this.http.put<CustomerDetailsResponse>(`${this.base}/customer-details/${id}`, dto);
  }
  deleteCustomer(id: number): Observable<void> {
    return this.http.delete<void>(`${this.base}/customer-details/${id}`);
  }

  // ── Customer Name ─────────────────────────────────────────────────────────
  getNames(): Observable<CustomerNameResponse[]> {
    return this.http.get<CustomerNameResponse[]>(`${this.base}/customer-name`);
  }
  createName(dto: CustomerNameRequest): Observable<CustomerNameResponse> {
    return this.http.post<CustomerNameResponse>(`${this.base}/customer-name`, dto);
  }
  updateName(id: number, dto: CustomerNameRequest): Observable<CustomerNameResponse> {
    return this.http.put<CustomerNameResponse>(`${this.base}/customer-name/${id}`, dto);
  }
  deleteName(id: number): Observable<void> {
    return this.http.delete<void>(`${this.base}/customer-name/${id}`);
  }

  // ── Customer Address ──────────────────────────────────────────────────────
  getAddresses(): Observable<CustomerAddressResponse[]> {
    return this.http.get<CustomerAddressResponse[]>(`${this.base}/customer-address`);
  }
  createAddress(dto: CustomerAddressRequest): Observable<CustomerAddressResponse> {
    return this.http.post<CustomerAddressResponse>(`${this.base}/customer-address`, dto);
  }
  updateAddress(id: number, dto: CustomerAddressRequest): Observable<CustomerAddressResponse> {
    return this.http.put<CustomerAddressResponse>(`${this.base}/customer-address/${id}`, dto);
  }
  deleteAddress(id: number): Observable<void> {
    return this.http.delete<void>(`${this.base}/customer-address/${id}`);
  }

  // ── Customer Contact Details ──────────────────────────────────────────────
  getContacts(): Observable<CustomerContactDetailsResponse[]> {
    return this.http.get<CustomerContactDetailsResponse[]>(`${this.base}/customer-contact-details`);
  }
  createContact(dto: CustomerContactDetailsRequest): Observable<CustomerContactDetailsResponse> {
    return this.http.post<CustomerContactDetailsResponse>(`${this.base}/customer-contact-details`, dto);
  }
  updateContact(id: number, dto: CustomerContactDetailsRequest): Observable<CustomerContactDetailsResponse> {
    return this.http.put<CustomerContactDetailsResponse>(`${this.base}/customer-contact-details/${id}`, dto);
  }
  deleteContact(id: number): Observable<void> {
    return this.http.delete<void>(`${this.base}/customer-contact-details/${id}`);
  }

  // ── Customer Identification ───────────────────────────────────────────────
  getIdentifications(): Observable<CustomerIdentificationResponse[]> {
    return this.http.get<CustomerIdentificationResponse[]>(`${this.base}/customer-identification`);
  }
  createIdentification(dto: CustomerIdentificationRequest): Observable<CustomerIdentificationResponse> {
    return this.http.post<CustomerIdentificationResponse>(`${this.base}/customer-identification`, dto);
  }
  updateIdentification(id: number, dto: CustomerIdentificationRequest): Observable<CustomerIdentificationResponse> {
    return this.http.put<CustomerIdentificationResponse>(`${this.base}/customer-identification/${id}`, dto);
  }
  deleteIdentification(id: number): Observable<void> {
    return this.http.delete<void>(`${this.base}/customer-identification/${id}`);
  }

  // ── Customer Classification ───────────────────────────────────────────────
  getClassifications(): Observable<CustomerClassificationResponse[]> {
    return this.http.get<CustomerClassificationResponse[]>(`${this.base}/customer-classification`);
  }
  createClassification(dto: CustomerClassificationRequest): Observable<CustomerClassificationResponse> {
    return this.http.post<CustomerClassificationResponse>(`${this.base}/customer-classification`, dto);
  }
  updateClassification(id: number, dto: CustomerClassificationRequest): Observable<CustomerClassificationResponse> {
    return this.http.put<CustomerClassificationResponse>(`${this.base}/customer-classification/${id}`, dto);
  }
  deleteClassification(id: number): Observable<void> {
    return this.http.delete<void>(`${this.base}/customer-classification/${id}`);
  }

  // ── Customer Proof of Identity ────────────────────────────────────────────
  getProofs(): Observable<CustomerProofOfIdentityResponse[]> {
    return this.http.get<CustomerProofOfIdentityResponse[]>(`${this.base}/customer-proof-of-identity`);
  }
  createProof(dto: CustomerProofOfIdentityRequest): Observable<CustomerProofOfIdentityResponse> {
    return this.http.post<CustomerProofOfIdentityResponse>(`${this.base}/customer-proof-of-identity`, dto);
  }
  updateProof(id: number, dto: CustomerProofOfIdentityRequest): Observable<CustomerProofOfIdentityResponse> {
    return this.http.put<CustomerProofOfIdentityResponse>(`${this.base}/customer-proof-of-identity/${id}`, dto);
  }
  deleteProof(id: number): Observable<void> {
    return this.http.delete<void>(`${this.base}/customer-proof-of-identity/${id}`);
  }
}
