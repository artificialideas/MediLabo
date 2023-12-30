import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

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
        private snackbar: MatSnackBar,
        private patientService: PatientService,
    ) {}

    ngOnInit(): void {
        this.newPatientForm = this.fb.group({
            firstName: new FormControl('', Validators.required),
            lastName: new FormControl('', Validators.required),
            birthdate: new FormControl('', Validators.required),
            gender: new FormControl('', Validators.required),
            phoneNumber: new FormControl(''),
            address: new FormControl(''),
        });
    }

    processRegister() {
        if (this.newPatientForm.valid) {
            let datePipe = new DatePipe('en-US');
            let birthdateFormatted = datePipe.transform(this.newPatientForm.get('birthdate')?.value, 'yyyy-MM-dd');
            
            const newPatient: Patient = {
                firstName: this.newPatientForm.get('firstName')?.value,
                lastName: this.newPatientForm.get('lastName')?.value,
                birthdate: birthdateFormatted ?? '',
                gender: this.newPatientForm.get('gender')?.value,
                phoneNumber: this.newPatientForm.get('phoneNumber')?.value,
                address: this.newPatientForm.get('address')?.value,
            };
            
            this.patientService.add(newPatient).subscribe((res) => {
                this.snackbar
                    .open(`${newPatient.firstName} ${newPatient.lastName} has been added to Patient's list`, undefined, {
                        duration: 3000
                    })
                    .afterOpened().subscribe(() => {
                            this.router.navigate(
                            ['/patients']
                        );
                    });
            });
        }
    }

    public goToList() {
        this.router.navigate(
            ['/patients']
        );
    }
}