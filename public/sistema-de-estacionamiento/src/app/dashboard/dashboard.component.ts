import { Component } from '@angular/core';
import { Automovilista } from '../interfaces/automovilista';
import { Reserva } from '../interfaces/reserva';
import { AutomovilsitaService } from '../automovilsita.service';
import { EstacionamientoService } from '../estacionamiento.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  automovilista?: Automovilista;
  estacionamientoActivo?: Reserva;

  constructor(private automovilistaService: AutomovilsitaService, private estacionamientoService: EstacionamientoService) { }


  ngOnInit() {
    this.getAutomovilista();
    this.getReserva();
  }

  getAutomovilista() {//:Automovilista{
    this.automovilistaService.getAutomovilista().subscribe(automovilista => this.automovilista = automovilista);
  }

  getReserva() {//:Reserva{
    this.estacionamientoService.getEstacionamiento().subscribe(estacionamiento =>{
       this.estacionamientoActivo = estacionamiento
      });
  }

  finalizarEstacionamiento(){
    this.estacionamientoService.finalizarEstacionamiento().subscribe(r=>{
      this.estacionamientoActivo = r;
      if (this.automovilista){ this.automovilista.cuentaCorriente.saldo - r.monto}
      this.estacionamientoActivo = undefined;
      localStorage.removeItem('id_estacionamiento_activo')
    });
  }

}
