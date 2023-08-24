import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, catchError } from 'rxjs';
import { Automovilista } from '../interfaces/automovilista';
import { NuevoAutomovilista } from '../interfaces/nuevo-automovilista';
import { baseUrl } from '../baseUrl';
import { ErrorHandlerService } from './error-handler.service';

@Injectable({
  providedIn: 'root'
})
export class AutomovilsitaService {

  private automovilistaUrl = baseUrl + "api/automovilistas/buscar/" + localStorage.getItem('telefono');
  private crearUrl = baseUrl + "api/automovilistas/crear";
  nuevoAutomovilsita?: NuevoAutomovilista;


  constructor(private http: HttpClient, private errorHandler: ErrorHandlerService) { }

  ngOnInit() {

  }
  getAutomovilista(): Observable<Automovilista> {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + localStorage.getItem('token')
    });
    return this.http.get<Automovilista>(this.automovilistaUrl, { headers })
      .pipe(catchError(this.errorHandler.handleError));
  }

  crearAutomovilista(telefono: string, contrasena: string, email: string): Observable<Automovilista> {
    this.nuevoAutomovilsita = {
      telefono: telefono,
      contrasena: contrasena,
      email: email
    }
    return this.http.post<Automovilista>(this.crearUrl, this.nuevoAutomovilsita)
      .pipe(catchError(this.errorHandler.handleError));
  }
}
