import { Component } from '@angular/core';
import { Patente } from '../../interfaces/patente';
import { Automovilista } from '../../interfaces/automovilista';
import { PatenteService } from '../../services/patente.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-agregar-patente',
  templateUrl: './agregar-patente.component.html',
  styleUrls: ['./agregar-patente.component.css']
})
export class AgregarPatenteComponent {

  constructor(private service: PatenteService, private router: Router) { }

  errorMessage?: String;

  ngOnInit() {
    if(localStorage.getItem('telefono') == undefined){
      this.router.navigate(['/login']);
    } 
  }

  save(patente: string) {

    this.service.crearPatente(patente).subscribe({
      next: () => this.router.navigate(['/iniciarEstacionamiento']),
      error: (e) => this.errorMessage = e
    })


  }
  patente: Patente = { id: 0, patente: '' };
  automovilista?: Automovilista;

}
