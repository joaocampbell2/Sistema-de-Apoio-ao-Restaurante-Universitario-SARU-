import { HttpClient } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router, type CanActivateFn } from '@angular/router';
import { catchError, map, of } from 'rxjs';

export const menuGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  
  const http = inject(HttpClient)

  return http.get<boolean>("http://localhost:8080/auth/verificaEhFuncionario").pipe(
    map(responser => {
      if (state.url === '/menu' && responser) {
        return router.parseUrl("/menu-funcionario");
      } else if (state.url === '/menu-funcionario' && !responser) {
        return router.parseUrl("/menu");
      }
      return true
    }),
    catchError(error => {
      router.navigate(["/home"]);
      return of(false); // Retorna um Observable com "false" para erros
    })
  );
  
};
