import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, forkJoin, map, mergeMap, switchMap } from 'rxjs';
import { Assessment } from 'src/app/models/assessment.model';

import { Patient } from 'src/app/models/patient.model';
import { AssessmentService } from 'src/app/services/assessment.service';
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
        private assessmentService: AssessmentService
    ) {}

    ngOnInit(): void {
        this.patientService.findAll().pipe(
            switchMap((res) => {
                const patients = res?.body ?? []; // Use optional chaining to handle null or undefined

                const patientObservables = patients.map((patient: any) => {
                return this.assessmentService.findRisk(patient.id).pipe(
                    // Map the assessment to the patient ID for later use
                    map((assessmentResponse: any) => {
                        // Use assessmentResponse.body to get the actual Assessment object -> endpoint returns Mono<Assessment>
                        const assessment = assessmentResponse.body;
                        return { assessment, patId: patient.id };
                      })
                );
            });
          
                // Use forkJoin to wait for all patientObservables to complete
                return forkJoin(patientObservables);
            })
        ).subscribe((results: any[]) => {
            results.forEach(({ assessment, patId }) => {
                const patient: any = this.patients.find((p: any) => p.id === patId);
                patient.risk = assessment.status;
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