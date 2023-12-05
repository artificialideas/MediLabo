import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

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
        private router: Router,
        private patientService: PatientService,
    ) {}

    ngOnInit(): void {
        this.patientService.findAll().subscribe((res) => {
            if (res) {
                let tmp: any = res.body
                this.patients = tmp;
            }
        });
    }

    public detailPatient(patient: Patient) {
        this.router.navigate(
            ['/detail', {
                firstname: patient.firstName,
                lastname: patient.lastName
            }]
        );
    }

    public editPatient(patient: Patient) {
        this.router.navigate(
            ['/edit' , {
                firstname: patient.firstName,
                lastname: patient.lastName
            }]
        );
    }

    public deletePatient(patient: Patient) {
        this.patientService.delete(patient.firstName, patient.lastName).subscribe((res) => {
            if (res) {
                let tmp: any = res.body
                this.patients = tmp;
            }
        });
    }

    public addPatient() {
        this.router.navigate(
            ['/add']
        );
    }
}