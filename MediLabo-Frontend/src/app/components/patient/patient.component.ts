import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';

import { Patient } from 'src/app/models/patient.model';
import { PatientService } from 'src/app/services/patient.service';
import { PatientFormComponent } from './patient-form/patient-form.component';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
    selector: 'app-patient',
    templateUrl: './patient.component.html',
    styleUrls: ['./patient.component.scss']
})
export class PatientComponent implements OnInit {
    public patients: Patient[] = [];

    constructor(
        private router: Router,
        private activatedRoute: ActivatedRoute,
        private patientService: PatientService,
        public dialog: MatDialog,
    ) {}

    ngOnInit(): void {
        this.patientService.findAll().subscribe((res) => {
            if (res) {
                let tmp: any = res.body
                this.patients = tmp;
            }
        });
    }

    goTo(firstname: string, lastname: string) {
        this.router.navigate(
            ['/patient'],
            {queryParams: { 
                firstname: firstname,
                lastname: lastname
            },
                //relativeTo: this.activatedRoute
            }
        );
    }

    public openForm() {
        const dialogRef = this.dialog.open(PatientFormComponent, {
            width: '600px',
            maxWidth: '90vw',
            maxHeight: '90vh',
        });
        dialogRef.afterClosed().subscribe((success: string) => {
            console.log("new user created", success)
        });
    }
}