import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { baseUrl } from '../baseUrl';
import { Observable } from 'rxjs';
import { Automovilista } from '../interfaces/automovilista';
import { LoginInterface } from '../interfaces/login-interface';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  url = baseUrl + "login"

  constructor(private http: HttpClient) { }

  login(loginData: LoginInterface): Observable<Automovilista> {
    return this.http.post<Automovilista>(this.url, loginData)
  }

}
