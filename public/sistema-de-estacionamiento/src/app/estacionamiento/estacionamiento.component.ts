import { Component } from '@angular/core';
import { Patente } from '../interfaces/patente';
import { EstacionamientoService } from '../services/estacionamiento.service';
import { Estacionamiento } from '../interfaces/estacionamiento';
import { PatenteService } from '../services/patente.service';

@Component({
  selector: 'app-estacionamiento',
  templateUrl: './estacionamiento.component.html',
  styleUrls: ['./estacionamiento.component.css']
})
export class EstacionamientoComponent {
  estacionamiento?: Estacionamiento;

  constructor(private service: EstacionamientoService, private patenteService: PatenteService){}

  patentes?: Patente[] = [];
  patenteSeleccionada?: Patente;


  ngOnInit(){
    this.patenteService.getPatentesAutomovilsita().subscribe(p => this.patentes = p)
  }

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
