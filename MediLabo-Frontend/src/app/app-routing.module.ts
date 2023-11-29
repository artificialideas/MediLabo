import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { PatientComponent } from './components/patient/patient.component';
import { PatientDetailComponent } from './components/patient/patient-detail/patient-detail.component';
import { Page404Component } from './components/patient/page-404/page-404.component';
import { PatientFormComponent } from './components/patient/patient-form/patient-form.component';

const routes: Routes = [
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
    path: 'detail',
    component: PatientDetailComponent,
    title: 'Patient details'
  },
  {
    path: 'add',
    component: PatientFormComponent,
    title: 'New patient'
  },
  { path: '**', component: Page404Component },  // Wildcard route for a 404 page
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}