import type { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {

  if (req.url != "http://localhost:8080/auth/login" && req.url != "http://localhost:8080/auth/cadastrarCliente"){

    const token = localStorage.getItem("token")
    const authenticatedReq =   req.clone({headers: req.headers.set("Authorization", "Bearer " + token)})
    return next(authenticatedReq);
  }

  return next(req);

};
