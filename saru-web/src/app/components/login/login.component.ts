import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {

  form: FormGroup;

  constructor(){

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
    }
    else{
      console.log("nao")
    }
  }


 }
