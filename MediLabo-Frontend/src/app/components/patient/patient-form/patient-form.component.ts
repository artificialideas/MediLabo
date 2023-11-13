import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

import { Patient } from 'src/app/models/patient.model';
import { PatientService } from 'src/app/services/patient.service';
import { PatientComponent } from '../patient.component';

@Component({
    selector: 'app-patient-form',
    templateUrl: './patient-form.component.html',
    styleUrls: ['./patient-form.component.scss']
})
export class PatientFormComponent implements OnInit {
    public patients: Patient[] = [];

    constructor(
        private patientService: PatientService,
        public dialogRef: MatDialogRef<PatientComponent>,
        @Inject(MAT_DIALOG_DATA) public data: Patient,
    ) {}

    ngOnInit(): void {
        console.log("received data from PatientComponent", this.data)
    }
}