import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Patente } from './interfaces/patente';
import { Reserva } from './interfaces/reserva';
import { baseUrl } from './baseUrl';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EstacionamientoService {
  

  urlIniciar: string = baseUrl + "reservas/iniciar";
  urlFinalizar: string = baseUrl + "reservas/finalizar";
  urlConseguir: string = baseUrl + "reservas/reservaActiva/";
  id_reserva?: string | null;
  estacionamiento?:Reserva ;

  iniciarEstacionamiento(patente: Patente | undefined ): Observable<Reserva> {
    return this.http.post<Reserva>(this.urlIniciar,{patente: patente?.patente, telefono: localStorage.getItem('telefono')})
  } 

  finalizarEstacionamiento(): Observable<Reserva> {
    return this.http.put<Reserva>(this.urlFinalizar,{id_reserva: localStorage.getItem('id_estacionamiento_activo')})
  }

  getEstacionamiento(): Observable<Reserva> { 
    return this.http.get<Reserva>(this.urlConseguir + localStorage.getItem('telefono'))

  }
  constructor(private http: HttpClient) { }
}
