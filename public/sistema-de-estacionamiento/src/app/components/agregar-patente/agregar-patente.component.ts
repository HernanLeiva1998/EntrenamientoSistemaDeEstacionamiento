import { Component } from '@angular/core';
import { Patente } from '../../interfaces/patente';
import { Driver } from '../../interfaces/driver';
import { PatenteService } from '../../services/patente.service';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-agregar-patente',
  templateUrl: './agregar-patente.component.html',
  styleUrls: ['./agregar-patente.component.css']
})
export class AgregarPatenteComponent {

  constructor(private service: PatenteService, 
    private router: Router,
    private authService: LoginService) { }

  errorMessage?: String;

  ngOnInit() {
    if (!this.authService.isAuthenticated()) {
      this.router.navigate(['/login']);
    }
  }

  save(patente: string) {

    this.service.crearPatente(patente).subscribe({
      next: () => this.router.navigate(['/iniciarEstacionamiento']),
      error: (e) => this.errorMessage = e
    })


  }
  patente: Patente = { id: 0, licensePlate: '' };
  automovilista?: Driver;

}
