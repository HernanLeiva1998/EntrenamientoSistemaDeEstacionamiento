import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-crear-automovilista',
  templateUrl: './crear-automovilista.component.html',
  styleUrls: ['./crear-automovilista.component.css']
})
export class CrearAutomovilistaComponent {
  telefono: string = '';
  contrasena: string = '';
  saldo: number = 0;

  constructor(private http: HttpClient) { }

  crearAutomovilista(): void {
    const automovilista = {
      telefono: this.telefono,
      contrasena: this.contrasena,
    };

    this.http.post('http://localhost:8080/automovilistas', automovilista)
      .subscribe(
        () => {
          console.log('Automovilista creado exitosamente');
        },
        error => {
          console.error('Error al crear el automovilista:', error);
        }
      );
  }
}

