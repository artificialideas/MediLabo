import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from './app-routing.module';

import { MaterialModule } from './material.module';

import { AppComponent } from './app.component';
import { PatientComponent } from './components/patient/patient.component';
import { PatientDetailComponent } from './components/patient/patient-detail/patient-detail.component';
import { PatientFormComponent } from './components/patient/patient-form/patient-form.component';

@NgModule({
  declarations: [
    AppComponent,
    PatientComponent,
    PatientDetailComponent,
    PatientFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    CommonModule,
    ReactiveFormsModule,
    NgbModule,
    MaterialModule
  ],
  exports: [
    PatientDetailComponent,
    PatientFormComponent],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
