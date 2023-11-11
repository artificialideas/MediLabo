import { Component, OnInit } from '@angular/core';

import { Patient } from 'src/app/models/patient.model';
import { PatientService } from 'src/app/services/patient.service';

@Component({
    selector: 'app-patient',
    templateUrl: './patient.component.html',
    styleUrls: ['./patient.component.scss']
})
export class PatientComponent implements OnInit {
    public patients: Patient[] = [];

    constructor(
        private patientService: PatientService
    ) {}

    ngOnInit(): void {
        this.patientService.findAll().subscribe((res) => {
            if (res) {
                let tmp: any = res.body
                this.patients = tmp;
            }
        });
    }
}