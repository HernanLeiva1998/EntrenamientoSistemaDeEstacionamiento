import { Component } from '@angular/core';
import { Patente } from '../interfaces/patente';
import { EstacionamientoService } from '../estacionamiento.service';
import { Reserva } from '../interfaces/reserva';

@Component({
  selector: 'app-iniciar-estacionamiento',
  templateUrl: './iniciar-estacionamiento.component.html',
  styleUrls: ['./iniciar-estacionamiento.component.css']
})
export class IniciarEstacionamientoComponent {
  estacionamiento?: Reserva;

  constructor(private service: EstacionamientoService){}

  patentes?: Patente[] = [{id:1, patente:"aaa111"}];
  patenteSeleccionada?: Patente;

  onSelect(patente: Patente) {
    this.patenteSeleccionada=patente;
  }
  iniciarEstacionamiento() {
   this.service.iniciarEstacionamiento(this.patenteSeleccionada).subscribe(r=>
    {
      this.estacionamiento=r; 
      if (this.estacionamiento){
        localStorage.setItem('id_estacionamiento_activo',  this.estacionamiento.id.toString());
      }
    });
  }

  finalizarEstacionamiento(){
    this.service.finalizarEstacionamiento().subscribe(r=>{
      this.estacionamiento=r
      localStorage.removeItem('id_estacionamiento_activo')
    });
    
  }



}
