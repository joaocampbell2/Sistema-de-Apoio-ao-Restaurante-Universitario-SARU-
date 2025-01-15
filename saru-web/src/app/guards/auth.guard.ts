import { inject } from '@angular/core';
import type { CanActivateFn } from '@angular/router';
import { Router } from '@angular/router';
export const authGuard: CanActivateFn = (route, state) => {

  const router = inject(Router);

  if(localStorage.getItem("token") != null){
    return true;

  }
  else{
    router.navigate(["/home"])
    return false;
  }

};
