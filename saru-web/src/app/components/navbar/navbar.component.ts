import { HttpClient } from '@angular/common/http';
import {  Component } from '@angular/core';
import {MatToolbarModule} from '@angular/material/toolbar';
import { RouterLink } from '@angular/router';
@Component({
  selector: 'app-navbar',
  imports: [MatToolbarModule, RouterLink],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss',
})

export class NavbarComponent {
  private httpClient;
  private saldo:any;
  constructor(httpClient: HttpClient){
    this.httpClient = httpClient;
    this.saldo = 0;
  }

  onInit(){
    this.httpClient.get("http//:localhost8080/cliente/resgatarDados").subscribe(response => {
      this.saldo = response
    })
  }

 }
