import { Component } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators, AbstractControl, ValidationErrors } from '@angular/forms';
import { CommonModule } from '@angular/common';
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
  
  constructor(private readonly fb: FormBuilder, private readonly router: Router, private readonly http: HttpClient) {
    this.cadastroForm = this.fb.group(
      {
        nome: ['', [Validators.required]],
        cpf: ['', [Validators.required, Validators.minLength(11), Validators.maxLength(11), this.validarCpf]],
        email: ['', [Validators.required, Validators.email]],
        senha: ['', [Validators.required, Validators.minLength(6)]],
        confirmarsenha: ['', [Validators.required]],
        tipoCliente: ['', [Validators.required]],
      },
      { validators: this.senhasIguais }
    );
  }

  validarCpf(control: AbstractControl): ValidationErrors | null {
    const cpf: string = control.value;
  
    if (!cpf) {
      return null;
    }
  
    if (!/^\d{11}$/.test(cpf)) {
      return { cpfTamanhoInvalido: true };
    }
  
    if (/^(\d)\1+$/.test(cpf)) {
      return { cpfInvalido: true };
    }
  
    const somaDigito = (mult: number[]): number => {
      return cpf
        .split('')
        .slice(0, mult.length)
        .reduce((acc: number, curr: string, idx: number) => acc + parseInt(curr, 10) * mult[idx], 0);
    };
  
    const digito1 = (somaDigito([10, 9, 8, 7, 6, 5, 4, 3, 2]) * 10) % 11 % 10;
    const digito2 = (somaDigito([11, 10, 9, 8, 7, 6, 5, 4, 3, 2]) * 10) % 11 % 10;
  
    if (digito1 !== parseInt(cpf[9]) || digito2 !== parseInt(cpf[10])) {
      return { cpfInvalido: true };
    }
  
    return null;
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
  
      this.http.post<string>("http://localhost:8080/auth/cadastrarCliente", cadastroDTO).subscribe({
        next: (response) => {
          alert('Cadastro realizado com sucesso!');
          this.router.navigateByUrl('/login');
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