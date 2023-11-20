import { NgModule } from '@angular/core';

import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import { MatDialogModule } from '@angular/material/dialog';

@NgModule({
  imports: [],
  exports: [
    MatButtonModule, 
    MatCardModule, 
    MatListModule, 
    MatDialogModule
  ],
})
export class MaterialModule {}
