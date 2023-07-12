import { Component } from '@angular/core';
import { Patente } from '../interfaces/patente';
import { Automovilista } from '../interfaces/automovilista';

@Component({
  selector: 'app-agregar-patente',
  templateUrl: './agregar-patente.component.html',
  styleUrls: ['./agregar-patente.component.css']
})
export class AgregarPatenteComponent {
  save() {

  }
  patente: Patente = { id: 0, patente: '' };
  automovilista?: Automovilista;

}
