import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppRoutingModule } from './app-routing.module';
import { MaterialModule } from './material.module';

import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { PatientComponent } from './components/patient/patient.component';
import { PatientDetailComponent } from './components/patient/patient-detail/patient-detail.component';
import { PatientFormComponent } from './components/patient/patient-form/patient-form.component';
import { PatientEditComponent } from './components/patient/patient-edit/patient-edit.component';

import { NoteListComponent } from './components/note/note-list/note-list.component';
import { NoteDialogComponent } from './components/note/note-dialog/note-dialog.component';

@NgModule({
  imports: [
    BrowserModule,
    HttpClientModule,
    CommonModule,
    ReactiveFormsModule,
    NgbModule,
    AppRoutingModule,
    MaterialModule
  ],
  declarations: [
    AppComponent,
    LoginComponent,
    PatientComponent,
    PatientDetailComponent,
    PatientEditComponent,
    PatientFormComponent,
    NoteListComponent,
    NoteDialogComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
