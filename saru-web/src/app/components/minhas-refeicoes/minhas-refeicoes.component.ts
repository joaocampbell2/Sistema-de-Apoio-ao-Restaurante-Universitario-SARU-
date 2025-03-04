import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RefeicaoEntityDTO } from '../../models/RefeicaoEntityDTO';
import { Router, RouterOutlet, RouterModule } from '@angular/router';
import { NavbarComponent } from '../navbar/navbar.component';
import { AlterarTurnoDTO } from '../../models/AlterarTurnoDTO';

declare var bootstrap: any;

@Component({
  selector: 'app-minhas-refeicoes',
  imports: [CommonModule, RouterOutlet, NavbarComponent,RouterModule],
  templateUrl: './minhas-refeicoes.component.html',
  styleUrl: './minhas-refeicoes.component.scss',
})
export class MinhasRefeicoesComponent {
    public refeicoes: Array<RefeicaoEntityDTO> = [];
    turnoAtual: string = '';
    turnoNovo: string = '';
    idRefeicaoSelecionada: number | null = null;
    qrCodeUrl: string = '';
    hoje:  number = Date.now()

    constructor(private readonly httpClient: HttpClient, private readonly router: Router){
      this.httpClient = httpClient;      
    }
  
    ngOnInit(){
      this.httpClient.get<Array<RefeicaoEntityDTO>>("http://localhost:8080/refeicao/minhasRefeicoes", {headers: {"Access-Control-Allow-Origin": "*","Authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIyOTU4NDg5NDY1NSIsInJvbGUiOiJBTFVOTyJ9.uK-JLH7gjKalUhowcpeg-_dkVQDrC5hkQyrZKI6lS2E"}}).subscribe(response => {
        this.refeicoes = response;
        
      })
    }

    alterarTurno(idRefeicao: number) {
      const refeicao = this.refeicoes.find((r: { idRefeicao: number; }) => r.idRefeicao === idRefeicao);
      if (refeicao) {
        this.idRefeicaoSelecionada = idRefeicao;
        this.turnoAtual = refeicao.turno;
        switch (refeicao.turno){
          case 'ALMOCO':
             this.turnoNovo = 'JANTAR';
             break;
          case 'JANTAR': 
              this.turnoNovo = 'ALMOCO';
              break;
          default: 
              break;

        }
  
        const modal = new bootstrap.Modal(document.getElementById('alterarTurnoModal')!);
        modal.show();
      }
    }
  
    confirmarAlteracao() {
      if (this.idRefeicaoSelecionada !== null) {  

        const alterarTurnoDTO: AlterarTurnoDTO ={idRefeicao: this.idRefeicaoSelecionada}
        this.httpClient.put('http://localhost:8080/refeicao/alterarTurno', alterarTurnoDTO, { responseType: 'text' }).subscribe(
          () => {
            const refeicao = this.refeicoes.find((r: { idRefeicao: number | null; }) => r.idRefeicao === this.idRefeicaoSelecionada);
            if (refeicao) {
              refeicao.turno = this.turnoNovo;
            }
            const modal = bootstrap.Modal.getInstance(document.getElementById('alterarTurnoModal')!);
            modal?.hide();
          },
          (error) => {
            console.error('Erro ao alterar turno:', error);
            alert("Impossível alterar turno!")
          }
        );
      }
    }
  
    qrCode(idRefeicao: number) {
      // Requisição genérica para obter o QR Code via API
      this.httpClient.get(`http://localhost:8080/refeicao/getQrCode/${idRefeicao}`, { responseType: 'blob' }).subscribe(
        (response) => {
          this.qrCodeUrl = URL.createObjectURL(response);
          const modal = new bootstrap.Modal(document.getElementById('qrCodeModal')!);
          modal.show();
        },
        (error) => {
          console.error('Erro ao obter QR Code:', error);
        }
      );
    }
  }
