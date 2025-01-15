import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import { loginDto } from '../../models/loginDTO';
import { Router } from '@angular/router';

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
      const loginDto: loginDto ={cpf: this.form.value.cpf,senha: this.form.value.senha}
      console.log(loginDto)
      this.http.post<string>("http://localhost:8080/auth/login",loginDto).subscribe(response => {
        console.log(response)
        localStorage.setItem("token",response)
        this.router.navigate(["/menu"])
      })
    }
    else{
      alert("login impossivel!")
    }
  }


 }
