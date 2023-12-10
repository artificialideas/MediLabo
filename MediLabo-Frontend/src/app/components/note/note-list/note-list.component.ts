import { Component, Input, OnInit } from "@angular/core";
import { MatDialog } from "@angular/material/dialog";
import { MatSnackBar } from "@angular/material/snack-bar";

import { Note } from "src/app/models/note.model";
import { Patient } from "src/app/models/patient.model";

import { NoteService } from "src/app/services/note.service";
import { NoteDialogComponent } from "../note-dialog/note-dialog.component";
import { EMPTY, concatMap, filter, map, of, switchMap, tap } from "rxjs";

@Component({
    selector: 'app-note-list',
    templateUrl: './note-list.component.html',
    styleUrls: ['./note-list.component.scss']
})
export class NoteListComponent implements OnInit {
    public notes: Note[] = [];

    @Input() patient: Patient | any;
    
    constructor(
        private dialog: MatDialog,
        private snackbar: MatSnackBar,
        private noteService: NoteService
    ) {}

    ngOnInit(): void {
        if (this.patient) {
            this.loadNotes();
        }
    }

    private loadNotes(): void {
        this.noteService.findNote(this.patient.id).pipe(
            map((res: any) => {
                if (res) {
                    return res.body.sort((
                        a: { date: string | number | Date; }, 
                        b: { date: string | number | Date; }) => new Date(b.date).getTime() - new Date(a.date).getTime());
                } else
                    return of([]);
            })
        ).subscribe((notes) => {
            if (notes) {
                this.notes = notes;
            }
        });
    }

    public addNote() {
        const dialogRef = this.dialog.open(NoteDialogComponent, {
            width: '600px',
            maxWidth: '100vw',
            maxHeight: '90vh',
            data: {
                patient: this.patient
            }
        });
        
        dialogRef.afterClosed().pipe(
            filter((result: Note) => result !== null && this.patient !== undefined),
            map((result: Note) => ({
                patId: this.patient?.id,
                patient: `${this.patient?.firstName} ${this.patient?.lastName}`,
                note: result.note
              })),
            concatMap((newNote: Note) => this.noteService.add(newNote)),
            tap(() => this.showSnackbar()),
            switchMap(() => {
                this.loadNotes(); // Use the loadNotes function
                return EMPTY; // Return an observable to satisfy the pipe
              })
        ).subscribe();
    }

    private showSnackbar(): void {
        this.snackbar.open(`A new note has been added for ${this.patient?.firstName} ${this.patient?.lastName}.`, undefined, {
            duration: 3000
        });
    }
}