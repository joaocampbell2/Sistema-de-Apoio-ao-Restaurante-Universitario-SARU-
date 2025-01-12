import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import { LoginDto } from '../../models/loginDTO';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {

  form: FormGroup;
  http: HttpClient
  constructor(http: HttpClient){
    this.http = http;
    this.form = new FormGroup(
      {
        cpf: new FormControl("",[Validators.required, Validators.maxLength(11),Validators.minLength(11)]),
        senha: new FormControl("",[Validators.required])
      }
    )
  }

  onSubmit(){

    if(this.form.valid){
      console.log(this.form.value)
      const loginDto = new LoginDto(this.form.value.cpf,this.form.value.senha) 
      this.http.post("https://localhost:8080/auth/login",{loginDto}).subscribe(response => {
        console.log(response)
      })
    }
    else{
      console.log("nao")
    }
  }


 }
