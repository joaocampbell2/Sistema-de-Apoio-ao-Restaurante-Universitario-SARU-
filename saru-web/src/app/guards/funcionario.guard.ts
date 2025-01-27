import { HttpClient } from '@angular/common/http';
import { inject } from '@angular/core';
import type { CanActivateFn } from '@angular/router';
import { Router } from '@angular/router';
import { catchError, map, of } from 'rxjs';
export const funcionarioGuard: CanActivateFn = (route, state) => {

  const router = inject(Router);

 const http = inject(HttpClient)
return http.get<boolean>("http://localhost:8080/auth/verificaEhFuncionario").pipe(map(
    responser=>{
      if(responser)
      {
       return true
     }else{
      alert("Acesso probido!")
      router.navigate(["/menu"])
      return false
     }
    }),
       catchError(error => {
         router.navigate(["/home"]);
         return of(false); // Retorna um Observable com "false" para erros
       })
     );
}

