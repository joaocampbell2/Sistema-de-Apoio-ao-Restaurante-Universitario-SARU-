import { Component } from '@angular/core';
import {MatMenuModule} from '@angular/material/menu';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';


@Component({
  selector: 'app-menu-funcionario',
  imports: [MatMenuModule,MatButtonModule,MatToolbarModule,],
  templateUrl: './menu-funcionario.component.html',
  styleUrl: './menu-funcionario.component.scss'
})
export class MenuFuncionarioComponent {

}
