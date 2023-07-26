import { Component } from '@angular/core';
import { Automovilista } from '../interfaces/automovilista';
import { Estacionamiento } from '../interfaces/estacionamiento';
import { AutomovilsitaService } from '../services/automovilsita.service';
import { EstacionamientoService } from '../services/estacionamiento.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  automovilista?: Automovilista;
  estacionamientoActivo?: Estacionamiento;

  constructor(private automovilistaService: AutomovilsitaService, private estacionamientoService: EstacionamientoService) { }


  ngOnInit() {
    this.getAutomovilista();
    this.getEstacionamiento();
  }

  getAutomovilista() {//:Automovilista{
    this.automovilistaService.getAutomovilista().subscribe(automovilista => this.automovilista = automovilista);
  }

  getEstacionamiento() {
    this.estacionamientoService.getEstacionamiento().subscribe(estacionamiento => {
      this.estacionamientoActivo = estacionamiento
    });
  }

  finalizarEstacionamiento() {
    this.estacionamientoService.finalizarEstacionamiento().subscribe(r => {
      this.estacionamientoActivo = r;
      if (this.automovilista) { this.automovilista.cuentaCorriente.saldo - r.monto }
      this.estacionamientoActivo = undefined;
      localStorage.removeItem('id_estacionamiento_activo')
      this.automovilista = r.automovilista
    });
  }

}
