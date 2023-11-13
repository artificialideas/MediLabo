import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { PatientComponent } from './components/patient/patient.component';
import { PatientDetailComponent } from './components/patient/patient-detail/patient-detail.component';

const routes: Routes = [
  { 
    path: 'patients', 
    component: PatientComponent,
    title: 'Patients list'
  },
  {
    path: 'patients/:firstname-:lastname',
    component: PatientDetailComponent,
    title: 'Patient details'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
