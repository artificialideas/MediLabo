import { Component, OnInit } from '@angular/core';

import { Patient } from 'src/app/models/patient.model';

@Component({
    selector: 'app-patient-form',
    templateUrl: './patient-form.component.html',
    styleUrls: ['./patient-form.component.scss']
})
export class PatientFormComponent implements OnInit {
    public patients: Patient[] = [];

    constructor() {}

    ngOnInit(): void {
        
    }
}