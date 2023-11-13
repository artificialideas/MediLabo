import { NgModule } from '@angular/core';

import { MatButtonModule } from '@angular/material';
import { MatDialogModule } from '@angular/material/dialog';

@NgModule({
  imports: [MatButtonModule, MatDialogModule],
  exports: [MatButtonModule, MatDialogModule],
})
export class MaterialModule {}
