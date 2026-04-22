import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { CustomerDetailsComponent } from './components/customer-details/customer-details.component';
import { CustomerNameComponent } from './components/customer-name/customer-name.component';
import { CustomerAddressComponent } from './components/customer-address/customer-address.component';
import { CustomerContactComponent } from './components/customer-contact/customer-contact.component';
import { CustomerIdentificationComponent } from './components/customer-identification/customer-identification.component';
import { CustomerClassificationComponent } from './components/customer-classification/customer-classification.component';
import { CustomerProofComponent } from './components/customer-proof/customer-proof.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'customers', component: CustomerDetailsComponent },
  { path: 'names', component: CustomerNameComponent },
  { path: 'addresses', component: CustomerAddressComponent },
  { path: 'contacts', component: CustomerContactComponent },
  { path: 'identification', component: CustomerIdentificationComponent },
  { path: 'classification', component: CustomerClassificationComponent },
  { path: 'proof-of-identity', component: CustomerProofComponent },
  { path: '**', redirectTo: '' }
];
