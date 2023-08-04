import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AutomovilsitaService } from '../services/automovilsita.service';

@Component({
  selector: 'app-crear-automovilista',
  templateUrl: './crear-automovilista.component.html',
  styleUrls: ['./crear-automovilista.component.css']
})
export class CrearAutomovilistaComponent {
  telefono: string = '';
  contrasena: string = '';
  email: string= '';
  cbu: string = '';
  mensaje?: string;
  

  constructor(private http: HttpClient, private service: AutomovilsitaService) { }

  crearAutomovilista(telefono: string, contrasena: string, email: string, cbu: string): void {

    this.service.crearAutomovilista(telefono, contrasena, email,cbu).subscribe({
      next:() =>{this.mensaje= "Creado"} ,
      error: (e) => { this.mensaje=e}
    })
  }
}

