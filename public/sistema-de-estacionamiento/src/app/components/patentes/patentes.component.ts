import { Component } from '@angular/core';
import { Patente } from '../../interfaces/patente';

@Component({
  selector: 'app-patentes',
  templateUrl: './patentes.component.html',
  styleUrls: ['./patentes.component.css']
})
export class PatentesComponent {
  patentes?: Patente[]
}
