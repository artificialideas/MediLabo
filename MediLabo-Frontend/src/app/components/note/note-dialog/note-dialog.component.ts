import { Component, Inject, OnInit } from "@angular/core";
import { FormBuilder, FormControl, FormGroup, Validators } from "@angular/forms";
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";

import { Patient } from "src/app/models/patient.model";

@Component({
    selector: 'app-note-dialog',
    templateUrl: './note-dialog.component.html',
    styleUrls: ['./note-dialog.component.scss'],
  })
export class NoteDialogComponent implements OnInit {
    public newNoteForm: FormGroup = new FormGroup({});
    
    constructor(
        public dialogRef: MatDialogRef<NoteDialogComponent>,
        @Inject(MAT_DIALOG_DATA) public patient: Patient,
        private fb: FormBuilder,
    ) {}

    ngOnInit(): void {
        this.newNoteForm = this.fb.group({
            note: new FormControl('', Validators.required),
        });
    }

    processRegister() {
        if (this.newNoteForm.valid) {
            this.dialogRef.close(this.newNoteForm.value);
        }
    }
}