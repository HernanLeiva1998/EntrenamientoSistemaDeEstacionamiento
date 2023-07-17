import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Automovilista } from './interfaces/automovilista';
import { baseUrl } from './baseUrl';

@Injectable({
  providedIn: 'root'
})
export class AutomovilsitaService {

  private automovilistaUrl = baseUrl + "automovilistas/buscar/" + localStorage.getItem('telefono');

  constructor(private http: HttpClient) {}

  ngOnInit(){
    
  }
  getAutomovilista(): Observable<Automovilista> {
    return this.http.get<Automovilista>(this.automovilistaUrl);
  }
}
