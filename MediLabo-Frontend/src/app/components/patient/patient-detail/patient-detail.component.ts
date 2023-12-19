import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { concatMap, of } from 'rxjs';

import { FullPatient } from 'src/app/models/patient.model';
import { AssessmentService } from 'src/app/services/assessment.service';

import { PatientService } from 'src/app/services/patient.service';

@Component({
    selector: 'app-patient-detail',
    templateUrl: './patient-detail.component.html',
    styleUrls: ['./patient-detail.component.scss']
})
export class PatientDetailComponent implements OnInit {
    public patient: FullPatient | any;

    constructor(
        private route: ActivatedRoute,
        private patientService: PatientService,
        private assessmentService: AssessmentService
    ) {}

    ngOnInit(): void {
        const firstname = this.route.snapshot.params['firstname'];
        const lastname = this.route.snapshot.params['lastname'];
        
        this.patientService.findPatient(firstname, lastname).pipe(
            concatMap((res) => {
                if (res) {
                    this.patient = res.body;
                    return this.assessmentService.findRisk(this.patient.id);
                } else
                    return of(null);
            })
        ).subscribe((res) => {
            if (res?.body) {
                this.patient.risk = res.body.status;
            }
        });
    }

    public relaunchAssessment(event: boolean) {
        if (event) {
            this.assessmentService.findRisk(this.patient.id).subscribe((res) => {
                if (res?.body) {
                    this.patient.risk = res.body.status;
                }
            });
        }
    }
}