import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; //
import { EnviarFeedbackComponent } from './enviar-feedback.component'; 
import { HttpClient, HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [EnviarFeedbackComponent], 
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    HttpClient,  
  ],
  exports: [EnviarFeedbackComponent]
})
export class VerRefeicoesModule { }
