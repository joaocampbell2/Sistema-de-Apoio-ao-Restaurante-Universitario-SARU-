import { Component } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { NavbarComponent } from '../navbar/navbar.component';

import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-menu-refeicoes',
  standalone: true,
  imports: [NavbarComponent,RouterModule],
  templateUrl: './menu-refeicoes.component.html',
  styleUrls: ['./menu-refeicoes.component.scss']
})
export class MenuRefeicoesComponent {
  http: HttpClient;


  constructor(http: HttpClient) {
    this.http = http;
  }
  

}
