import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { concatMap, of } from 'rxjs';

import { FullPatient, Patient } from 'src/app/models/patient.model';
import { AssessmentService } from 'src/app/services/assessment.service';
import { PatientService } from 'src/app/services/patient.service';

@Component({
    selector: 'app-patient-edit',
    templateUrl: './patient-edit.component.html',
    styleUrls: ['./patient-edit.component.scss']
})
export class PatientEditComponent implements OnInit {
    public editPatientForm: FormGroup = new FormGroup({});
    public patient: FullPatient | any;

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private fb: FormBuilder,
        private snackbar: MatSnackBar,
        private patientService: PatientService,
        private assessmentService: AssessmentService
    ) {}

    ngOnInit(): void {
        const firstname = this.route.snapshot.params['firstname'];
        const lastname = this.route.snapshot.params['lastname'];
        this.editPatientForm = this.fb.group({
            firstName: new FormControl({ value: '', disabled: true }),
            lastName: new FormControl({ value: '', disabled: true }),
            birthdate: new FormControl({ value: '', disabled: true }),
            gender: new FormControl('', Validators.required),
            phoneNumber: new FormControl(''),
            address: new FormControl(''),
        });
        
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
                this.initForm(this.patient);
            }
        });
    }

    initForm(patient: Patient) {
        this.editPatientForm.patchValue({
            firstName: patient.firstName,
            lastName: patient.lastName,
            birthdate: patient.birthdate,
            gender: patient.gender,
            phoneNumber: patient.phoneNumber,
            address: patient.address,
        });
    }

    processRegister() {
        if (this.editPatientForm.valid) {
            let datePipe = new DatePipe('en-US');
            let birthdateFormatted = datePipe.transform(this.editPatientForm.get('birthdate')?.value, 'yyyy-MM-dd');
            
            const patient: Patient = {
                firstName: this.editPatientForm.get('firstName')?.value,
                lastName: this.editPatientForm.get('lastName')?.value,
                birthdate: birthdateFormatted ?? '',
                gender: this.editPatientForm.get('gender')?.value,
                phoneNumber: this.editPatientForm.get('phoneNumber')?.value,
                address: this.editPatientForm.get('address')?.value,
            };
            
            this.patientService.update(patient.firstName, patient.lastName, patient).subscribe((res) => {
                this.snackbar
                    .open(`${patient.firstName} ${patient.lastName} has been edited`, undefined, {
                        duration: 3000
                    });
                this.goToList();
            });
        }
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

    public goToList() {
        this.router.navigate(
            ['/']
        );
    }
}