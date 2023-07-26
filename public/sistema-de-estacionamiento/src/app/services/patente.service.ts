import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';

import { Patente } from '../interfaces/patente';
import { baseUrl } from '../baseUrl';
import { Observable, catchError, throwError, of } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class PatenteService {

  urlPatentes = baseUrl + 'api/patentes/buscar/';
  urlAgregarPatente = baseUrl + 'api/patentes/agregar';


  constructor(private http: HttpClient) { }

  getPatentesAutomovilsita(): Observable<Patente[]> {
    return this.http.get<Patente[]>(this.urlPatentes + localStorage.getItem('telefono'))
  }

  crearPatente(patente: string): Observable<Patente> {
    return this.http
      .post<Patente>(this.urlAgregarPatente, { patente: patente, telefono: localStorage.getItem('telefono') })
      .pipe(
        catchError(this.handleError)
      )
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${error.status}, body was: `, error.error);
    }
    // Return an observable with a user-facing error message.
    return throwError(() => new Error(error.error.error));
  }
}
