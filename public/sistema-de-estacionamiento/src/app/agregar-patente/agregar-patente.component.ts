import { Component } from '@angular/core';
import { Patente } from '../interfaces/patente';
import { Automovilista } from '../interfaces/automovilista';
import { PatenteService } from '../services/patente.service';

@Component({
  selector: 'app-agregar-patente',
  templateUrl: './agregar-patente.component.html',
  styleUrls: ['./agregar-patente.component.css']
})
export class AgregarPatenteComponent {

  constructor(private service: PatenteService){}

  save(patente: string) {
    this.service.crearPatente(patente).subscribe()
  }
  patente: Patente = { id: 0, patente: '' };
  automovilista?: Automovilista;

}
