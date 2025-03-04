import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AvisoDTO } from '../../models/AvisoDTO';
import { RouterOutlet } from '@angular/router';
@Component({
  selector: 'app-verAvisos',
  imports: [CommonModule, RouterOutlet],
  templateUrl: './ver-aviso.component.html',
  styleUrl: './ver-aviso.component.scss',
})
export class VerAvisoComponent {
    private readonly httpClient;
    public avisos: any;
    constructor(httpClient: HttpClient){
      this.httpClient = httpClient;      
    }
  
    ngOnInit(){
      this.httpClient.get<Array<AvisoDTO>>("http://localhost:8080/aviso/buscarAvisos", {headers: {"Access-Control-Allow-Origin": "*","Authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIyOTU4NDg5NDY1NSIsInJvbGUiOiJBTFVOTyJ9.uK-JLH7gjKalUhowcpeg-_dkVQDrC5hkQyrZKI6lS2E"}}).subscribe(response => {
        this.avisos = response;
        
      })
    }
 }
