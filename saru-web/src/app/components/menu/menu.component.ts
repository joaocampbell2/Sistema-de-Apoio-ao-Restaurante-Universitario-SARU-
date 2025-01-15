import { Component } from '@angular/core';
import { MaterialModule } from '../../material.module';
import {MatMenuModule} from '@angular/material/menu';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';


@Component({
  selector: 'app-menu',
  imports: [MatButtonModule, MatMenuModule, MatToolbarModule,MatIconModule ],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.scss'
})
export class MenuComponent {}

