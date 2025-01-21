import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import { LoginDto } from '../../models/LoginDTO';
import { Router } from '@angular/router';
import { TokenDTO } from '../../models/TokenDTO';
@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {

  form: FormGroup;
  http: HttpClient
  router: Router;
  constructor(http: HttpClient, router:Router){
    this.http = http;
    this.form = new FormGroup(
      {
        cpf: new FormControl("",[Validators.required, Validators.maxLength(11),Validators.minLength(11)]),
        senha: new FormControl("",[Validators.required])
      }
    )
    this.router = router;
  }

  onSubmit(){

    if(this.form.valid){
      const loginDto: LoginDto ={cpf: this.form.value.cpf,senha: this.form.value.senha}
      console.log(loginDto)
      this.http.post<TokenDTO>("http://localhost:8080/auth/login",loginDto).subscribe({next: response => {
        console.log(response)
        this.http.get<Boolean>("http://localhost:8080/auth/verificaEhFuncionario").subscribe({
          next: responser=>{
            if(responser)
            {
              localStorage.setItem("token",response.token)
              this.router.navigate(["/menu-funcionario"])
            }else{
              localStorage.setItem("token",response.token)
              this.router.navigate(["/menu"])
            }
          }
        })
        
      },
      error: () =>{
        alert("Erro!")
      }
      }
    )
    }
    else{
      alert("login impossivel!")
    }
  }


 }
