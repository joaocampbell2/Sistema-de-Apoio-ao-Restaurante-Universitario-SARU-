import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { NavbarComponent } from '../navbar/navbar.component';
import { RefeicaoDTO } from '../../models/RefeicaoDTO';
import { Turno } from '../../models/enums/Turno.enum';

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
  minDate: string;

  constructor(private readonly http: HttpClient) {
    this.http = http;
    this.form = new FormGroup({
      dataRefeicao: new FormControl('',[Validators.required]),
      turno: new FormControl(Turno,[Validators.required])
    })
    const today = new Date();
    this.minDate = today.toISOString().split('T')[0];
  }

  comprarRefeicao() {

    if(this.form.valid){
      const refeicaoDto: RefeicaoDTO ={dataRefeicao: this.form.value.dataRefeicao, turno: this.form.value.turno}
      console.log(refeicaoDto)
      this.http.post("http://localhost:8080/refeicao/comprarRefeicao",refeicaoDto,{headers:{"Accept":"image/png"},responseType: "blob"}).subscribe(response => {
      const url = URL.createObjectURL(response);
      this.resultado = url;
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
