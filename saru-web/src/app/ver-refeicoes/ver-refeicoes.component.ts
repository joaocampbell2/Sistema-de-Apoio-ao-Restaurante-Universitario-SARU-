import { Component } from '@angular/core';
import { RefeicoesService } from '../ver-refeicoes/refeicoes.service';
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
          console.log(this.resultado)
        },
        (_error: any) => {
          this.resultado = 'Erro ao obter as refeições. Tente novamente mais tarde.';
        }
      );
    } else {
      this.resultado = 'Por favor, insira uma data e um turno.';
    }
  }
}
