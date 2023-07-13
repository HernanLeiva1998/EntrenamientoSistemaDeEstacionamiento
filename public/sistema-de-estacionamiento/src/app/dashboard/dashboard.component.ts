import { Component } from '@angular/core';
import { Automovilista } from '../interfaces/automovilista';
import { Reserva } from '../interfaces/reserva';
import { AutomovilsitaService } from '../automovilsita.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  automovilista?: Automovilista;
  estacionamientoActivo?: Reserva;

  constructor(private automovilistaService: AutomovilsitaService) { }

  ngOnInit() {
    this.automovilistaService.getAutomovilista().subscribe(automovilista => this.automovilista = automovilista)
  }

  getAutomovilsita() {//:Automovilista{
    this.automovilistaService.getAutomovilista()
  }
  getReserva() {//:Reserva{

  }

}
