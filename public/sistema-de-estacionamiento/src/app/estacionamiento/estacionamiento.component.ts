import { Component } from '@angular/core';
import { Patente } from '../interfaces/patente';
import { EstacionamientoService } from '../services/estacionamiento.service';
import { Estacionamiento } from '../interfaces/estacionamiento';
import { PatenteService } from '../services/patente.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-estacionamiento',
  templateUrl: './estacionamiento.component.html',
  styleUrls: ['./estacionamiento.component.css']
})
export class EstacionamientoComponent {

  message?: string;
  estacionamiento?: Estacionamiento;


  constructor(private service: EstacionamientoService, private patenteService: PatenteService, private router: Router) { }

  patentes?: Patente[] = [];
  patenteSeleccionada?: Patente;
  estacionamientoYaIniciado: boolean = false;


  ngOnInit() {

    if(localStorage.getItem('telefono') == undefined){
      this.router.navigate(['/login']);
    } 
    

    this.patenteService.getPatentesAutomovilsita().subscribe(p => this.patentes = p)
    this.service.getEstacionamiento().subscribe({
      next: (e) => {this.estacionamientoYaIniciado = true
        this.estacionamiento = e}
    })
  }

  onSelect(patente: Patente) {
    this.patenteSeleccionada = patente;
  }
  iniciarEstacionamiento() {
    this.service.iniciarEstacionamiento(this.patenteSeleccionada).subscribe({next: (e) => 
      {
        this.estacionamiento = e
        if (this.estacionamiento) {
          localStorage.setItem('id_estacionamiento_activo', this.estacionamiento.id.toString());
        }
        this.estacionamientoYaIniciado = true;
      },
      error: (e) => this.message = e
    });
  }

  finalizarEstacionamiento() {
    this.service.finalizarEstacionamiento().subscribe({ next:(e) => 
      {
        this.estacionamiento = e
        localStorage.removeItem('id_estacionamiento_activo')
        this.estacionamientoYaIniciado = false
      }
    });

  }



}
