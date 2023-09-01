import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, catchError } from 'rxjs';
import { Driver } from '../interfaces/driver';
import { NuevoAutomovilista } from '../interfaces/nuevo-automovilista';
import { baseUrl } from '../baseUrl';
import { ErrorHandlerService } from './error-handler.service';
import { Token } from '../interfaces/token';

@Injectable({
  providedIn: 'root'
})
export class AutomovilsitaService {

  private automovilistaUrl = baseUrl + "api/automovilistas/buscar";
  private crearUrl = baseUrl + "auth/register";
  nuevoAutomovilsita?: NuevoAutomovilista;


  constructor(private http: HttpClient, private errorHandler: ErrorHandlerService) { }

  ngOnInit() {

  }
  getAutomovilista(): Observable<Driver> {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + localStorage.getItem('token')
    });
    return this.http.get<Driver>(this.automovilistaUrl, { headers })
      .pipe(catchError(this.errorHandler.handleError));
  }

  crearAutomovilista(telefono: string, contrasena: string, email: string): Observable<Token> {
    this.nuevoAutomovilsita = {
      username: telefono,
      password: contrasena,
      email: email
    }
    return this.http.post<Token>(this.crearUrl, this.nuevoAutomovilsita)
      .pipe(catchError(this.errorHandler.handleError));
  }
}
