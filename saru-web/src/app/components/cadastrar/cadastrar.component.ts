import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl, ValidationErrors } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { CadastroDTO } from '../../models/cadastroDTO';

@Component({
  selector: 'app-cadastrar',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './cadastrar.component.html',
  styleUrls: ['./cadastrar.component.scss'],
})
export class CadastrarComponent {
  cadastroForm: FormGroup;
  
  constructor(private fb: FormBuilder, private router: Router, private http: HttpClient) {
    this.cadastroForm = this.fb.group(
      {
        nome: ['', [Validators.required]],
        cpf: ['', [Validators.required, Validators.minLength(11), Validators.maxLength(11)]],
        email: ['', [Validators.required, Validators.email]],
        senha: ['', [Validators.required, Validators.minLength(6)]],
        confirmarsenha: ['', [Validators.required]],
        tipoCliente: ['', [Validators.required]],
      },
      { validators: this.senhasIguais }
    );
  }

  senhasIguais(group: AbstractControl): ValidationErrors | null {
    const senha = group.get('senha')?.value;
    const confirmarsenha = group.get('confirmarsenha')?.value;
    return senha && confirmarsenha && senha === confirmarsenha ? null : { senhasNaoIguais: true };
  }

  
  onSubmit() {
    if (this.cadastroForm.valid) {
      const cadastroDTO: CadastroDTO = this.cadastroForm.value;
  
      console.log('Enviando ao backend:', cadastroDTO);
  
      this.http.post("http://localhost:8080/auth/cadastrarCliente", cadastroDTO).subscribe({
        next: (response) => {
          console.log('Resposta do servidor:', response);
          alert('Cadastro realizado com sucesso!');
          //this.router.navigateByUrl('/login');
        },  
        error: (error) => {
          console.error('Erro ao cadastrar:', error);
          alert('Erro ao realizar o cadastro.');
        }
      });
    } else {
      this.cadastroForm.markAllAsTouched();
      console.error('Formulário inválido.');
    }
  }
}