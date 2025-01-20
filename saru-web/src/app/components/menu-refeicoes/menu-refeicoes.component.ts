import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AvaliacaoDTO } from '../../models/AvaliacaoDTO';
import { HttpClient } from '@angular/common/http';
import { NavbarComponent } from '../navbar/navbar.component';
import { RefeicaoDTO } from '../../models/RefeicaoDTO';
import { ImageLoader } from '@angular/common';
import { RouterLink, RouterModule, RouterOutlet } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatMenuModule } from '@angular/material/menu';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-menu-refeicoes',
  standalone: true,
  imports: [MatButtonModule, MatMenuModule, MatToolbarModule,MatIconModule,RouterOutlet,RouterModule, NavbarComponent],
  templateUrl: './menu-refeicoes.component.html',
  styleUrls: ['./menu-refeicoes.component.scss']
})
export class MenuRefeicoesComponent {
  http: HttpClient;


  constructor(http: HttpClient) {
    this.http = http;
  }
  

}
