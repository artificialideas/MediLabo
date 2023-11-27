import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Patient } from 'src/app/models/patient.model';
import { PatientService } from 'src/app/services/patient.service';

@Component({
    selector: 'app-patient-detail',
    templateUrl: './patient-detail.component.html',
    styleUrls: ['./patient-detail.component.scss']
})
export class PatientDetailComponent implements OnInit {
    public patient: Patient | any;

    constructor(
        private patientService: PatientService,
        private route: ActivatedRoute
    ) {console.log("hi!!!")}

    ngOnInit(): void {
        console.log("hi")
        /* const firstname = this.route.snapshot.params['firstname'];
        const lastname = this.route.snapshot.params['lastname'];
        
        this.patientService.findPatient(firstname, lastname).subscribe((patient) => {
            console.log(patient)
            this.patient = patient;
        }); */
    }
}