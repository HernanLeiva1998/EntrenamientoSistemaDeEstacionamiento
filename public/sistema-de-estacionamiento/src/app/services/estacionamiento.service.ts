import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
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
  estacionamiento?:Estacionamiento ;

  iniciarEstacionamiento(patente: Patente | undefined ): Observable<Estacionamiento> {
    return this.http.post<Estacionamiento>(this.urlIniciar,{patente: patente?.patente, telefono: localStorage.getItem('telefono')})
    .pipe(
      catchError(this.errorHandler.handleError)
    )
  } 

  finalizarEstacionamiento(): Observable<Estacionamiento> {
    return this.http.put<Estacionamiento>(this.urlFinalizar,{telefono: localStorage.getItem('telefono')})
  }

  getEstacionamiento(): Observable<Estacionamiento> { 
    return this.http.get<Estacionamiento>(this.urlConseguir + localStorage.getItem('telefono'))

  }
  constructor(private http: HttpClient, private errorHandler: ErrorHandlerService) { }
}
