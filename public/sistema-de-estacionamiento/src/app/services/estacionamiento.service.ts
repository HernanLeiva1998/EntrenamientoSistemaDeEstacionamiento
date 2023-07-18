import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Patente } from '../interfaces/patente';
import { Estacionamiento } from '../interfaces/estacionamiento';
import { baseUrl } from '../baseUrl';
import { Observable } from 'rxjs';

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
  } 

  finalizarEstacionamiento(): Observable<Estacionamiento> {
    return this.http.put<Estacionamiento>(this.urlFinalizar,{id_estacionamiento: localStorage.getItem('id_estacionamiento_activo')})
  }

  getEstacionamiento(): Observable<Estacionamiento> { 
    return this.http.get<Estacionamiento>(this.urlConseguir + localStorage.getItem('telefono'))

  }
  constructor(private http: HttpClient) { }
}
