import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; //
import { VerRefeicoesComponent } from './ver-refeicoes.component'; 
import { HttpClient, HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [VerRefeicoesComponent], 
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    HttpClient,  
  ],
  exports: [VerRefeicoesComponent]
})
export class VerRefeicoesModule { }
