import { HttpClient } from '@angular/common/http';
import {  Component, OnInit } from '@angular/core';
import {MatToolbarModule} from '@angular/material/toolbar';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-navbar',
  imports: [MatToolbarModule,RouterModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss',
})

export class NavbarComponent implements OnInit {
  
  public saldo:any = 0;
  constructor(private readonly httpClient: HttpClient){
    this.httpClient = httpClient;
  }

  ngOnInit(){
    this.httpClient.get("http://localhost:8080/cliente/getSaldo", {headers: {"Access-Control-Allow-Origin": "*","Authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIyOTU4NDg5NDY1NSIsInJvbGUiOiJBTFVOTyJ9.uK-JLH7gjKalUhowcpeg-_dkVQDrC5hkQyrZKI6lS2E"}}).subscribe(response => {
      console.log(response)
      this.saldo = response
    })
  }

 }
