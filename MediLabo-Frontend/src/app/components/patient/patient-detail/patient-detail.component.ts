import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { concatMap, of } from 'rxjs';

import { Patient } from 'src/app/models/patient.model';
import { Note } from 'src/app/models/note.model';

import { PatientService } from 'src/app/services/patient.service';
import { NoteService } from 'src/app/services/note.service';

import { NoteDialogComponent } from '../../note/note-dialog/note-dialog.component';

@Component({
    selector: 'app-patient-detail',
    templateUrl: './patient-detail.component.html',
    styleUrls: ['./patient-detail.component.scss']
})
export class PatientDetailComponent implements OnInit {
    public patient: Patient | any;
    public notes: Note | any;

    constructor(
        private route: ActivatedRoute,
        private dialog: MatDialog,
        private patientService: PatientService,
        private noteService: NoteService
    ) {}

    ngOnInit(): void {
        const firstname = this.route.snapshot.params['firstname'];
        const lastname = this.route.snapshot.params['lastname'];
        
        this.patientService.findPatient(firstname, lastname).pipe(
            concatMap((res) => {
                if (res) {
                    this.patient = res.body;
                    return this.noteService.findNote(this.patient.id);
                }
                return of(null);
            })
        ).subscribe((res) => {
            if (res)
                this.notes = res.body;
        });
    }

    public addNote() {
      const dialogRef = this.dialog.open(NoteDialogComponent, {
        width: '600px',
        maxWidth: '100vw',
        maxHeight: '90vh',
        data: {
            patId: this.patient.id
        }
      });
      dialogRef.afterClosed().subscribe((success: string) => {});
    }
}