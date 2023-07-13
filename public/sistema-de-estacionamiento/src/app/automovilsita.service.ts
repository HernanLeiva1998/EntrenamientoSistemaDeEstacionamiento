import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Automovilista } from './interfaces/automovilista';

@Injectable({
  providedIn: 'root'
})
export class AutomovilsitaService {

  private automovilistaUrl = "http://localhost:8080/automovilistas/buscar/2213331111"

  constructor(private http: HttpClient) { }

  getAutomovilista(): Observable<Automovilista> {
    return this.http.get<Automovilista>(this.automovilistaUrl);
  }
}
