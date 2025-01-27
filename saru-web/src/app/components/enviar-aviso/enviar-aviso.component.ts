import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-enviar-aviso',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './enviar-aviso.component.html',
  styleUrls: ['./enviar-aviso.component.scss']
})
export class EnviarAvisosComponent {
  public avisoForm: FormGroup;
  public mensagem: string | null = null;

  constructor(private readonly fb: FormBuilder, private readonly httpClient: HttpClient) {
    this.avisoForm = this.fb.group({
      texto: ['', [Validators.required]],
      data: ['', [Validators.required]],
    });
  }

  enviarAviso() {
    if (this.avisoForm.valid) {
      const aviso = this.avisoForm.value;

      this.httpClient.post('http://localhost:8080/aviso/publicarAvisos', aviso, 
        {responseType: 'text',
      }).subscribe({
        next: (response: string) => {
          this.mensagem = response; 
          this.avisoForm.reset();
          this.hideMessageAfterTimeout();
        },
        error: (err: any) => {
            console.error('Erro ao publicar aviso:', err);
            this.mensagem = err.error?.text || 'Erro ao publicar aviso. Tente novamente mais tarde.';
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
}
