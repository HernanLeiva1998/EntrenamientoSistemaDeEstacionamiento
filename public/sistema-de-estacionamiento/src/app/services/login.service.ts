import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { baseUrl } from '../baseUrl';
import { Observable, catchError } from 'rxjs';
import { Automovilista } from '../interfaces/automovilista';
import { LoginInterface } from '../interfaces/login-interface';
import { ErrorHandlerService } from './error-handler.service';
import { Router } from '@angular/router';
import { Token } from '../interfaces/token';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

  url = baseUrl + "auth/login"

  constructor(private http: HttpClient, private router: Router, private errorHandler: ErrorHandlerService) { }

  login(loginData: LoginInterface): Observable<Token> {
    let headers: Headers
    headers = new Headers()
    headers.append('Authorization', 'Bearer ' + '')
    return this.http.post<Token>(this.url, { password: loginData.contrasena, username: loginData.telefono }).pipe(
      catchError(this.errorHandler.handleError)
    )
  }

  logout() {
    let headers: Headers
    headers = new Headers()
    headers.append('Authorization', 'Bearer ' + '')
    localStorage.removeItem("token");
    this.router.navigate(['/login'])
  }

}
