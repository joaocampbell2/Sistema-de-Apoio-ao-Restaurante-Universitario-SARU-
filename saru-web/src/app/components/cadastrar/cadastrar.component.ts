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
      console.log('Formulário enviado com sucesso:', this.cadastroForm.value);


      this.http.post("http://localhost:8080/auth/cadastrarCliente", this.cadastroForm.value as CadastroDTO)

      alert('Cadastro realizado com sucesso!');

      this.router.navigateByUrl('/login');
    } else {
      this.cadastroForm.markAllAsTouched();
      console.error('Formulário inválido.');
    }
  }
}