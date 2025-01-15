import { NgModule } from '@angular/core';
import{MatAutocompleteModule} from'@angular/material/autocomplete';
import {MatToolbar, MatToolbarModule } from '@angular/material/toolbar';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';

@NgModule({
  exports: [
    MatAutocompleteModule,
    MatToolbarModule,
    MatMenuModule,
    MatIconModule,
    MatButtonModule,
  ],
})
export class MaterialModule {}
