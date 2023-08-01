import { Component } from '@angular/core';
import { Automovilista } from '../interfaces/automovilista';
import { Estacionamiento } from '../interfaces/estacionamiento';
import { AutomovilsitaService } from '../services/automovilsita.service';
import { EstacionamientoService } from '../services/estacionamiento.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  automovilista?: Automovilista;
  estacionamientoActivo?: Estacionamiento;
  errorMessage?: String;

  constructor(private automovilistaService: AutomovilsitaService, private estacionamientoService: EstacionamientoService,
    private router: Router) { }


  ngOnInit() {
    if(localStorage.getItem('telefono') == undefined){
      this.router.navigate(['/login']);
    } 
    this.getAutomovilista();
    this.getEstacionamiento();
  }

  getAutomovilista() {//:Automovilista{
    this.automovilistaService.getAutomovilista().subscribe({
      next: (automovilista) => this.automovilista = automovilista,
      error: (e) =>  this.errorMessage = e     
    });
  }

  getEstacionamiento() {
    this.estacionamientoService.getEstacionamiento().subscribe({
      next: (estacionamiento) =>  this.estacionamientoActivo = estacionamiento
    });
  }

  finalizarEstacionamiento() {
    this.estacionamientoService.finalizarEstacionamiento().subscribe({
      next: (r) => {this.estacionamientoActivo = r;
      if (this.automovilista) { this.automovilista.cuentaCorriente.saldo - r.monto }
      this.estacionamientoActivo = undefined;
      localStorage.removeItem('id_estacionamiento_activo')
      this.automovilista = r.automovilista
      },
      error: (e) => this.errorMessage= e
    });
  }

}
