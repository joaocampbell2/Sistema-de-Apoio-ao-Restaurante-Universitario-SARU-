import { Component } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { NavbarComponent } from '../navbar/navbar.component';

import { MatButtonModule } from '@angular/material/button';
import { MatMenuModule } from '@angular/material/menu';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-menu-refeicoes',
  standalone: true,
  imports: [MatButtonModule, MatMenuModule, MatToolbarModule,MatIconModule, NavbarComponent],
  templateUrl: './menu-refeicoes.component.html',
  styleUrls: ['./menu-refeicoes.component.scss']
})
export class MenuRefeicoesComponent {
  http: HttpClient;


  constructor(http: HttpClient) {
    this.http = http;
  }
  

}
