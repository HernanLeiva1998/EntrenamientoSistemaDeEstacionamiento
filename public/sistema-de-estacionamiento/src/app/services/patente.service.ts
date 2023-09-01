import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';

import { Patente } from '../interfaces/patente';
import { baseUrl } from '../baseUrl';
import { Observable, catchError, throwError, of } from 'rxjs';
import { ErrorHandlerService } from './error-handler.service';


@Injectable({
  providedIn: 'root'
})
export class PatenteService {

  urlPatentes = baseUrl + 'api/patentes/buscar';
  urlAgregarPatente = baseUrl + 'api/patentes/agregar';


  constructor(private http: HttpClient, private errorHandlerService: ErrorHandlerService) { }

  getPatentesAutomovilsita(): Observable<Patente[]> {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + localStorage.getItem('token')
    });
    return this.http.get<Patente[]>(this.urlPatentes, { headers })
  }

  crearPatente(patente: string): Observable<Patente> {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + localStorage.getItem('token')
    });
    return this.http
      .post<Patente>(this.urlAgregarPatente, { licensePlate: patente, phone: localStorage.getItem('telefono') }, { headers })
      .pipe(
        catchError(this.errorHandlerService.handleError)
      )
  }


}
