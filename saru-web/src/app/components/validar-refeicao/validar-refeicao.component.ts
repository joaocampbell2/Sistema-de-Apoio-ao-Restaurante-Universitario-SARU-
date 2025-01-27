import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-validar-refeicao',
  imports: [ReactiveFormsModule,CommonModule],
  templateUrl: './validar-refeicao.component.html',
  styleUrl: './validar-refeicao.component.scss',
})
export class ValidarRefeicaoComponent implements OnInit{ 
    public qrCodeForm: FormGroup;
    public mensagem: string | null = null;
  
    constructor(private readonly fb: FormBuilder, private readonly httpClient: HttpClient, private readonly route: ActivatedRoute) {
      this.qrCodeForm = this.fb.group({
        token: ['', [Validators.required]],
      });
    }


    validarToken(){
      if (this.qrCodeForm.valid) {
        const token = this.qrCodeForm.value.token;
        console.log(token)
  
        this.httpClient.get('http://localhost:8080/refeicao/validarQrCode/' + token, 
          {responseType: 'text',
        }).subscribe({
          next: (response: string) => {
            this.mensagem = "Refeição válida!"; 
            this.qrCodeForm.reset();
          },
          error: (err: any) => {
              console.error('Erro ao verificar token:', err);
              this.mensagem ='Token Inválido. Tente outro.';
              this.hideMessageAfterTimeout();

          },
        });
      } else {
        this.mensagem = 'Por favor, preencha todos os campos obrigatórios.';
        this.hideMessageAfterTimeout();
      }
    }

    hideMessageAfterTimeout() {
      setTimeout(() => {
        this.mensagem = null;
      }, 10000);
    }


    ngOnInit(): void {
      this.route.paramMap.subscribe(params =>{
        this.qrCodeForm.patchValue({
          token : params.get("token"),
        })
      })
    }

}
  

