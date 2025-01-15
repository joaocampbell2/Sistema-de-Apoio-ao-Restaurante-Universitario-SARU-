import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RefeicoesService {

  private baseUrl = 'http://localhost:8080/refeicao/verRefeicoes';

  constructor(private http: HttpClient) { 
    
  }

  getRefeicoes(dataRefeicao: string, turno: string): Observable<string> {
    const url = `${this.baseUrl}/${dataRefeicao}/${turno}`;
    return this.http.get<string>(url);
  }
}
