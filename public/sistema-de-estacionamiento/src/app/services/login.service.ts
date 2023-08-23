import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { baseUrl } from '../baseUrl';
import { Observable, catchError } from 'rxjs';
import { Automovilista } from '../interfaces/automovilista';
import { LoginInterface } from '../interfaces/login-interface';
import { ErrorHandlerService } from './error-handler.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  url = baseUrl + "login"

  constructor(private http: HttpClient, private router: Router, private errorHandler: ErrorHandlerService) { }

  login(loginData: LoginInterface): Observable<Automovilista> {
    let headers: Headers
    headers=new Headers()
    headers.append('Authorization', 'Basic ' + btoa('user:password'))
    return this.http.post<Automovilista>(this.url, {contrasena: loginData.contrasena, telefono: loginData.telefono ,headers: headers}).pipe(
      catchError(this.errorHandler.handleError)
    )
  } 

  logout() {
    localStorage.removeItem("telefono");
    this.router.navigate(['/login'])
  }
  
}
