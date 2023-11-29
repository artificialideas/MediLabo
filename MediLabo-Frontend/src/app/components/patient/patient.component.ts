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

    public goToDetail(firstname: string, lastname: string) {
        this.router.navigate(
            ['/detail', {
                firstname: firstname,
                lastname: lastname
            }]
        );
    }

    public goToForm() {
        this.router.navigate(
            ['/add']
        );
    }
}