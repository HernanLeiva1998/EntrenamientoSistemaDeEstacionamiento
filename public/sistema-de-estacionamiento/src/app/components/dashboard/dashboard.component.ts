import { Component } from '@angular/core';
import { Driver } from '../../interfaces/driver';
import { Estacionamiento } from '../../interfaces/estacionamiento';
import { AutomovilsitaService } from '../../services/automovilsita.service';
import { EstacionamientoService } from '../../services/estacionamiento.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  automovilista?: Driver;
  estacionamientoActivo?: Estacionamiento;
  errorMessage?: String;

  constructor(private automovilistaService: AutomovilsitaService, private estacionamientoService: EstacionamientoService,
    private router: Router) { }


  ngOnInit() {
    if (localStorage.getItem('telefono') == undefined) {
      this.router.navigate(['/login']);
    } else {
      this.getAutomovilista();
      this.getEstacionamiento();
    }
  }

  getAutomovilista() {//:Automovilista{
    this.automovilistaService.getAutomovilista().subscribe({
      next: (automovilista) => this.automovilista = automovilista,
      error: (e) => this.errorMessage = e
    });
  }

  getEstacionamiento() {
    this.estacionamientoService.getEstacionamiento().subscribe({
      next: (estacionamiento) => this.estacionamientoActivo = estacionamiento
    });
  }

  finalizarEstacionamiento() {
    this.estacionamientoService.finalizarEstacionamiento().subscribe({
      next: (r) => {
        this.estacionamientoActivo = r;
        if (this.automovilista) {
          this.automovilista.wallet.balance = this.automovilista.wallet.balance - r.totalCost
        }
        this.estacionamientoActivo = undefined;
        localStorage.removeItem('id_estacionamiento_activo')

      },
      error: (e) => this.errorMessage = e
    });
  }

}
