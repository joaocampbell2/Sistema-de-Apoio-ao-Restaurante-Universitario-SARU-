import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { NavbarComponent } from '../navbar/navbar.component';
import { RefeicaoDTO } from '../../models/RefeicaoDTO';
import { SaldoDTO } from '../../models/SaldoDTO';

@Component({
  selector: 'app-adicionar-saldo',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, NavbarComponent],
  templateUrl: './adicionar-saldo.component.html',
  styleUrls: ['./adicionar-saldo.component.scss']
})
export class AdicionarSaldoComponent {
  form: FormGroup;
  resultado: any;
  resultadoClass: string = '';

  constructor(private http: HttpClient) {
    this.http = http;
    this.form = new FormGroup({
      valor: new FormControl('', Validators.required)
    })
  }
  
  
  
  adicionarSaldo() {

    if(this.form.valid){
      const saldoDTO: SaldoDTO ={valor: this.form.value.valor}
      console.log(saldoDTO)
      this.http.put("http://localhost:8080/cliente/adicionarSaldo",saldoDTO).subscribe(response => {
      console.log(this.resultado);
      })
    }
    }
  

  hideMessageAfterTimeout(){
    setTimeout(()=> {
      this.resultado = '';
    }, 10000)
  
  }
}
