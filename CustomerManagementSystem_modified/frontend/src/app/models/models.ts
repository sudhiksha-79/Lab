// ── Customer Details ──────────────────────────────────────────────────────────
export interface CustomerDetailsRequest {
  customerType: string;
  customerDOB: string;
  customerStatus: string;
  customerGender: string;
  customerPreferredLanguage: string;
  customerCountryOrigin: string;
  effectiveDate: string;
  userID: string;
  workstationID: string;
  programID: string;
  uuid: string;
}

export interface CustomerDetailsResponse {
  customerIdentifier: number;
  customerType: string;
  customerDOB: string;
  customerStatus: string;
  customerGender: string;
  customerPreferredLanguage: string;
  customerCountryOrigin: string;
  effectiveDate: string;
  crudValue: string;
  userID: string;
  workstationID: string;
  programID: string;
  hostTimestamp: string;
  localTimestamp: string;
  uuid: string;
}

// ── Customer Name ─────────────────────────────────────────────────────────────
export interface CustomerNameRequest {
  customerIdentifier: number;
  customerNameComponentType: string;
  customerNameComponentValue: string;
  effectiveDate: string;
  crudValue: string;
  userID: string;
  workstationID: string;
  programID: string;
  uuid: string;
}

export interface CustomerNameResponse {
  id: number;
  customerIdentifier: number;
  customerNameComponentType: string;
  customerNameComponentValue: string;
  effectiveDate: string;
  crudValue: string;
  userID: string;
  workstationID: string;
  programID: string;
  uuid: string;
}

// ── Customer Address ──────────────────────────────────────────────────────────
export interface CustomerAddressRequest {
  customerIdentifier: number;
  customerNameComponentType: string;
  customerNameComponentValue: string;
  effectiveDate: string;
  crudValue: string;
  userID: string;
  workstationID: string;
  programID: string;
  uuid: string;
}

export interface CustomerAddressResponse {
  id: number;
  customerIdentifier: number;
  customerNameComponentType: string;
  customerNameComponentValue: string;
  effectiveDate: string;
  crudValue: string;
  userID: string;
  workstationID: string;
  programID: string;
  uuid: string;
}

// ── Customer Contact Details ──────────────────────────────────────────────────
export interface CustomerContactDetailsRequest {
  customerId: number;
  customerContactDetails: string;
  startDate: string;
  endDate: string;
  crudValue: string;
  userID: string;
  workstationID: string;
  programID: string;
  uuid: string;
}

export interface CustomerContactDetailsResponse {
  customerContactID: number;
  customerId: number;
  customerContactDetails: string;
  startDate: string;
  endDate: string;
  crudValue: string;
  userID: string;
  workstationID: string;
  programID: string;
  uuid: string;
}

// ── Customer Identification ───────────────────────────────────────────────────
export interface CustomerIdentificationRequest {
  customerIdentificationType: string;
  customerIdentificationItem: string;
  effectiveDate: string;
  crudValue: string;
  userID: string;
  workstationID: string;
  programID: string;
  uuid: string;
}

export interface CustomerIdentificationResponse {
  customerIdentificationID: number;
  customerIdentificationType: string;
  customerIdentificationItem: string;
  effectiveDate: string;
  crudValue: string;
  userID: string;
  workstationID: string;
  programID: string;
  uuid: string;
}

// ── Customer Classification ───────────────────────────────────────────────────
export interface CustomerClassificationRequest {
  customerClassificationType: string;
  customerClassificationTypeValue: string;
  effectiveDate: string;
  crudValue: string;
  userID: string;
  workstationID: string;
  programID: string;
  uuid: string;
}

export interface CustomerClassificationResponse {
  id: number;
  customerClassificationType: string;
  customerClassificationTypeValue: string;
  effectiveDate: string;
  crudValue: string;
  userID: string;
  workstationID: string;
  programID: string;
  uuid: string;
}

// ── Customer Proof of Identity ────────────────────────────────────────────────
export interface CustomerProofOfIdentityRequest {
  customerIdentifier: number;
  customerClassificationTypeValue: string;
  startDate: string;
  endDate: string;
  crudValue: string;
  userID: string;
  workstationID: string;
  programID: string;
  uuid: string;
}

export interface CustomerProofOfIdentityResponse {
  id: number;
  customerIdentifier: number;
  customerClassificationTypeValue: string;
  startDate: string;
  endDate: string;
  crudValue: string;
  userID: string;
  workstationID: string;
  programID: string;
  uuid: string;
}
