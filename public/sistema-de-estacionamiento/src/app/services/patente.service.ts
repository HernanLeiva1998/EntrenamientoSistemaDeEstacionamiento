import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Patente } from '../interfaces/patente';
import { baseUrl } from '../baseUrl';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class PatenteService {

  urlPatentes= baseUrl + 'api/patentes/buscar/';
  urlAgregarPatente= baseUrl + 'api/patentes/agregar';


  constructor(private http: HttpClient) { }

  getPatentesAutomovilsita(): Observable<Patente[]>{
    return this.http.get<Patente[]>(this.urlPatentes + localStorage.getItem('telefono'))
  }

  crearPatente(patente: string): Observable<Patente>{
    return this.http.post<Patente>(this.urlAgregarPatente,{patente: patente,telefono:localStorage.getItem('telefono') })
  }
}
