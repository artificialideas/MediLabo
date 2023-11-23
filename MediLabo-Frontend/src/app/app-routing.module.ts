import { Routes } from '@angular/router';

import { PatientComponent } from './components/patient/patient.component';
import { PatientDetailComponent } from './components/patient/patient-detail/patient-detail.component';

export const routes: Routes = [
  /* { path: '', 
    redirectTo: 'patients', 
    pathMatch: 'full' 
  }, */
  { 
    path: '', 
    component: PatientComponent,
    title: 'Patients list'
  },
  {
    path: 'patient/:firstname-:lastname',
    component: PatientDetailComponent,
    title: 'Patient details'
  }
];
