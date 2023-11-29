import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';

@NgModule({
  imports: [
    BrowserAnimationsModule
  ],
  exports: [
    MatButtonModule, 
    MatCardModule, 
    MatListModule, 
  ],
})
export class MaterialModule {}
