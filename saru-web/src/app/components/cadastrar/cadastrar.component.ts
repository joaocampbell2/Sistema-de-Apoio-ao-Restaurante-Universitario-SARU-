import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, ReactiveFormsModule, ValidationErrors, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CadastroDTO } from '../../models/cadastroDTO';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-Cadastrar',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './Cadastrar.component.html',
  styleUrl: './Cadastrar.component.scss',
})
export class CadastrarComponent {
  form: FormGroup;
  http: HttpClient;
  router: Router;

  constructor(http: HttpClient, router: Router) {
    this.http = http;
    this.router = router;
    this.form = new FormGroup(
      {
        nome: new FormControl('', [Validators.required]),
        cpf: new FormControl('', [
          Validators.required,
          Validators.minLength(11),
          Validators.maxLength(11),
          this.validarCpf.bind(this),
        ]),
        email: new FormControl('', [
          Validators.required,
          Validators.email,
        ]),
        senha: new FormControl('', [
          Validators.required,
          Validators.minLength(6),
        ]),
        confirmarSenha: new FormControl('', [
          Validators.required,
        ]),
        tipoCliente: new FormControl('', [Validators.required]),
      },
      { validators: this.senhasIguais }
    );
  }

  onSubmit() {
    if (this.form.valid) {
      const cadastroDTO: CadastroDTO = this.form.value;

      console.log('Enviando ao backend:', cadastroDTO);

      this.http.post<string>('http://localhost:8080/auth/cadastrarCliente', cadastroDTO).subscribe({
        next: () => {
          alert('Cadastro realizado com sucesso!');
          this.router.navigateByUrl('/login');
        },
        error: (error) => {
          console.error('Erro ao cadastrar:', error);
          alert('Erro ao realizar o cadastro.');
        },
      });
    } else {
      this.form.markAllAsTouched();
      console.error('Formulário inválido.');
    }
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
    const confirmarSenha = group.get('confirmarSenha')?.value;
    return senha && confirmarSenha && senha === confirmarSenha ? null : { senhasNaoIguais: true };
  }
  
}