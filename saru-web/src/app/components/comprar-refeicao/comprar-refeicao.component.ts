import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AvaliacaoDTO } from '../../models/AvaliacaoDTO';
import { HttpClient } from '@angular/common/http';
import { NavbarComponent } from '../navbar/navbar.component';
import { RefeicaoDTO } from '../../models/RefeicaoDTO';

@Component({
  selector: 'app-comprar-refeicao',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, NavbarComponent],
  templateUrl: './comprar-refeicao.component.html',
  styleUrls: ['./comprar-refeicao.component.scss']
})
export class ComprarRefeicaoComponent {
  form: FormGroup;
  resultado: any;
  resultadoClass: string = '';
  http: HttpClient;
  minDate: string;

  constructor(http: HttpClient) {
    this.http = http;
    this.form = new FormGroup({
      dataRefeicao: new FormControl('',[Validators.required]),
      turno: new FormControl('',[Validators.required])
    })
    const today = new Date();
    this.minDate = today.toISOString().split('T')[0];
  }

  comprarRefeicao() {

    if(this.form.valid){
      const refeicaoDto: RefeicaoDTO ={dataRefeicao: this.form.value.dataRefeicao, turno: this.form.value.turno}
      console.log(refeicaoDto)
      this.http.post<string>("http://localhost:8080/refeicao/comprarRefeicao",refeicaoDto).subscribe(response => {
        console.log(response)
        this.resultado = response;
      })
    }
    }
  

  hideMessageAfterTimeout(){
    setTimeout(()=> {
      this.resultado = '';
    }, 10000)
  
  }
}
