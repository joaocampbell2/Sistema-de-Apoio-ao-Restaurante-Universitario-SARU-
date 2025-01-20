import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AvaliacaoDTO } from '../../models/AvaliacaoDTO';
import { HttpClient } from '@angular/common/http';
import { NavbarComponent } from '../navbar/navbar.component';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-enviar-feedback',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, NavbarComponent],
  templateUrl: './enviar-feedback.component.html',
  styleUrls: ['./enviar-feedback.component.scss']
})
export class EnviarFeedbackComponent implements OnInit {
  form: FormGroup;
  resultado: any;
  resultadoClass: string = '';

  constructor(private readonly http: HttpClient, private readonly route: ActivatedRoute) {
    this.http = http;
    this.form = new FormGroup({
      dataRefeicao: new FormControl('',[Validators.required]),
      turno: new FormControl('',[Validators.required]),
      nota: new FormControl('',[Validators.required]),
      feedback: new FormControl('',[Validators.required])
    })
  }

  enviarFeedback() {

    if(this.form.valid){
      const avaliacaoDto: AvaliacaoDTO ={dataRefeicao: this.form.value.dataRefeicao, turno: this.form.value.turno, nota: this.form.value.nota, feedback: this.form.value.feedback}
      console.log(avaliacaoDto)
      this.http.post<string>("http://localhost:8080/publicarAvaliacoes",avaliacaoDto).subscribe(response => {
        console.log(response)
        this.resultado = response;
      })
    }
    }
  
    ngOnInit(): void {
      this.route.paramMap.subscribe(params =>{
        this.form.patchValue({
          dataRefeicao : params.get("data"),
          turno: params.get("turno")
        })
      })
    }


  hideMessageAfterTimeout(){
    setTimeout(()=> {
      this.resultado = '';
    }, 10000)
  
  }
}
