import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-prueba',
  templateUrl: './prueba.component.html',
  styleUrls: ['./prueba.component.css']
})
export class PruebaComponent {
  respuesta: any;

  constructor(private http: HttpClient) { }
  llamarEndpoint(): Observable<any> {
    const url = 'http://localhost:8080/prueba'; // Ruta del endpoint en el servidor Spring
    return this.http.get(url);
  }

  ngOnInit() {
    this.llamarEndpoint().subscribe(
      response => {
        this.respuesta = response.mensaje; // Asignar la respuesta a la propiedad
      },
      error => {
        this.respuesta = error; // Manejar cualquier error
      }
    );
  }

}


