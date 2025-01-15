import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { CommonModule } from '@angular/common';
import { AvisoDTO } from '../../models/AvisoDTO';

@Component({
  selector: 'app-verAvisos',
  imports: [NavbarComponent],
  templateUrl: './verAvisos.component.html',
  styleUrl: './verAvisos.component.scss',
})
export class VerAvisosComponent {
    private httpClient;
    public avisos: any;
    constructor(httpClient: HttpClient){
      this.httpClient = httpClient;      
    }
  
    ngOnInit(){
      this.httpClient.get<Array<AvisoDTO>>("http://localhost:8080/avisos/buscarAvisos", {headers: {"Access-Control-Allow-Origin": "*","Authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIyOTU4NDg5NDY1NSIsInJvbGUiOiJBTFVOTyJ9.uK-JLH7gjKalUhowcpeg-_dkVQDrC5hkQyrZKI6lS2E"}}).subscribe(response => {
        console.log(response)
        this.avisos = response;
        
      })
    }
 }
