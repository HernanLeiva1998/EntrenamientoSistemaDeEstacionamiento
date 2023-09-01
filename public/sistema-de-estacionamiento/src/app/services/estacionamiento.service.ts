import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Patente } from '../interfaces/patente';
import { Estacionamiento } from '../interfaces/estacionamiento';
import { baseUrl } from '../baseUrl';
import { Observable, catchError } from 'rxjs';
import { ErrorHandlerService } from './error-handler.service';

@Injectable({
  providedIn: 'root'
})
export class EstacionamientoService {


  urlIniciar: string = baseUrl + "api/estacionamientos/iniciar";
  urlFinalizar: string = baseUrl + "api/estacionamientos/finalizar";
  urlConseguir: string = baseUrl + "api/estacionamientos/estacionamientoActivo/";
  id_estacionamiento?: string | null;
  estacionamiento?: Estacionamiento;

  iniciarEstacionamiento(patente: Patente | undefined): Observable<Estacionamiento> {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + localStorage.getItem('token')
    });
    return this.http.post<Estacionamiento>(this.urlIniciar,
      { licensePlate: patente?.licensePlate, phone: localStorage.getItem('telefono') }, { headers })
      .pipe(
        catchError(this.errorHandler.handleError)
      )
  }

  finalizarEstacionamiento(): Observable<Estacionamiento> {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + localStorage.getItem('token')
    });
    return this.http.put<Estacionamiento>(this.urlFinalizar,
      { telefono: localStorage.getItem('telefono') }, { headers })
  }

  getEstacionamiento(): Observable<Estacionamiento> {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + localStorage.getItem('token')
    });
    return this.http.get<Estacionamiento>(this.urlConseguir + localStorage.getItem('telefono'), { headers })

  }
  constructor(private http: HttpClient, private errorHandler: ErrorHandlerService) { }
}
