import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';

import { Patente } from '../interfaces/patente';
import { baseUrl } from '../baseUrl';
import { Observable, catchError, throwError, of } from 'rxjs';
import { ErrorHandlerService } from './error-handler.service';


@Injectable({
  providedIn: 'root'
})
export class PatenteService {

  urlPatentes = baseUrl + 'api/patentes/buscar/';
  urlAgregarPatente = baseUrl + 'api/patentes/agregar';


  constructor(private http: HttpClient, private errorHandlerService: ErrorHandlerService) { }

  getPatentesAutomovilsita(): Observable<Patente[]> {
    return this.http.get<Patente[]>(this.urlPatentes + localStorage.getItem('telefono'))
  }

  crearPatente(patente: string): Observable<Patente> {
    return this.http
      .post<Patente>(this.urlAgregarPatente, { patente: patente, telefono: localStorage.getItem('telefono') })
      .pipe(
        catchError(this.errorHandlerService.handleError)
      )
  }


}
