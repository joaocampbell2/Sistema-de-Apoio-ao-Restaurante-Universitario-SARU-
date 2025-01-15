import { Component } from '@angular/core';
import { RefeicoesService } from './refeicoes.service';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-ver-refeicoes',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './ver-refeicoes.component.html',
  styleUrls: ['./ver-refeicoes.component.scss']
})
export class VerRefeicoesComponent {
  refeicoesForm: FormGroup;
  resultado: any;
  resultadoClass: string = '';

  constructor(private refeicoesService: RefeicoesService) {
    this.refeicoesForm = new FormGroup({

      dataRefeicao: new FormControl(''),
      turno: new FormControl('')
    })
  }

  buscarRefeicoes() {

    const { dataRefeicao, turno } = this.refeicoesForm.value;

    if (dataRefeicao && turno) {
      this.refeicoesService.getRefeicoes(dataRefeicao, turno).subscribe(
        (response) => {
          this.resultado = response;
          this.resultado = `Refeições encontradas: ${response}`;
          this.resultadoClass = 'success';
          this.hideMessageAfterTimeout();

        },
        (_error: any) => {
          this.resultado = 'Erro ao obter as refeições. Selecione uma data existente, ou tente novamente mais tarde';
          this.resultadoClass = 'error';
          this.hideMessageAfterTimeout();

        }
      );
    } else {
      this.resultado = 'Por favor, insira uma data e um turno.';
      this.resultadoClass = 'warning';
      this.hideMessageAfterTimeout();

    }
  }

  hideMessageAfterTimeout(){
    setTimeout(()=> {
      this.resultado = '';
    }, 10000)
  
  }
}
