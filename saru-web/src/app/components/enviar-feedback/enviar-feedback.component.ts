import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AvaliacaoDTO } from '../../models/AvaliacaoDTO';
import { HttpClient } from '@angular/common/http';
import { NavbarComponent } from '../navbar/navbar.component';

@Component({
  selector: 'app-enviar-feedback',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, NavbarComponent],
  templateUrl: './enviar-feedback.component.html',
  styleUrls: ['./enviar-feedback.component.scss']
})
export class EnviarFeedbackComponent {
  form: FormGroup;
  resultado: any;
  resultadoClass: string = '';
  http: HttpClient;

  constructor(http: HttpClient) {
    this.http = http;
    this.form = new FormGroup({
      dataRefeicao: new FormControl('',[Validators.required]),
      turno: new FormControl('',[Validators.required]),
      nota: new FormControl('',[Validators.required]),
      avaliacao: new FormControl('',[Validators.required])
    })
  }

  enviarFeedback() {

    if(this.form.valid){
      const avaliacaoDto: AvaliacaoDTO ={data: this.form.value.dataRefeicao, turno: this.form.value.turno, nota: this.form.value.nota, texto: this.form.value.avaliacao}
      console.log(avaliacaoDto)
      this.http.post<String>("http://localhost:8080/publicarAvaliacoes",avaliacaoDto).subscribe(response => {
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
