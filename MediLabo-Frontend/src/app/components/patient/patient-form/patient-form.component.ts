import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import * as _moment from 'moment';
import { Moment } from 'moment'; 
const moment = _moment;

import { Patient } from 'src/app/models/patient.model';
import { PatientService } from 'src/app/services/patient.service';

@Component({
    selector: 'app-patient-form',
    templateUrl: './patient-form.component.html',
    styleUrls: ['./patient-form.component.scss']
})
export class PatientFormComponent implements OnInit {
    public newPatientForm: FormGroup = new FormGroup({});

    constructor(
        private router: Router,
        private fb: FormBuilder,
        private patientService: PatientService,
    ) {}

    ngOnInit(): void {
        this.newPatientForm = this.fb.group({
            firstName: new FormControl('', Validators.required),
            lastName: new FormControl('', Validators.required),
            birthdate: new FormControl(moment(), Validators.required),
            gender: new FormControl('', Validators.required),
            phoneNumber: new FormControl('', Validators.required),
            address: new FormControl('', Validators.required),
        });
    }

    processRegister() {
        if (this.newPatientForm.valid) {
            const newPatient: Patient = {
                firstName: this.newPatientForm.get('firstName')?.value,
                lastName: this.newPatientForm.get('lastName')?.value,
                birthdate: this.newPatientForm.get('birthdate')?.value,
                gender: this.newPatientForm.get('gender')?.value,
                phoneNumber: this.newPatientForm.get('phoneNumber')?.value,
                address: this.newPatientForm.get('address')?.value,
            };
console.log("newPatient",newPatient)
            //this.patientService.add(newPatient).subscribe(res => console.log(res));
        }
    }

    public goToList() {
        this.router.navigate(
            ['/']
        );
    }
}