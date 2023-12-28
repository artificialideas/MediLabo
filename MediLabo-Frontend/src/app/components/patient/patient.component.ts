import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, forkJoin, map, mergeMap, of } from 'rxjs';

import { FullPatient, Patient } from 'src/app/models/patient.model';
import { AssessmentService } from 'src/app/services/assessment.service';
import { AuthService } from 'src/app/services/auth.service';
import { PatientService } from 'src/app/services/patient.service';

@Component({
    selector: 'app-patient',
    templateUrl: './patient.component.html',
    styleUrls: ['./patient.component.scss']
})
export class PatientComponent implements OnInit {
    public patients: FullPatient[] = [];

    constructor(
        private router: Router,
        private patientService: PatientService,
        private assessmentService: AssessmentService,
        public authService: AuthService
    ) {}

    ngOnInit(): void {
        this.patientService.findAll().pipe(
            mergeMap((res) => {
                if (res?.body) {
                    this.patients = res.body;
                    
                    const requests: Observable<any>[] = this.patients.map((patient: any) =>
                        this.assessmentService.findRisk(patient.id).pipe(
                            // Extract the body of the HTTP response
                            map(response => response.body)
                        )
                    );
                    return forkJoin(requests);
                } else
                    return of(null);
            })
        ).subscribe((results) => {
            if (results)
                results.forEach((assessment, index) => {
                    this.patients[index].risk = assessment.status;
                });
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