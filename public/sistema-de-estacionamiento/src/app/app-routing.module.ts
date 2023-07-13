import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AutomovilistasComponent } from './automovilistas/automovilistas.component';
import { LoginComponent } from './login/login.component';
import { CrearAutomovilistaComponent } from './crear-automovilista/crear-automovilista.component';
import { PruebaComponent } from './prueba/prueba.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AgregarPatenteComponent } from './agregar-patente/agregar-patente.component';
import { IniciarEstacionamientoComponent } from './iniciar-estacionamiento/iniciar-estacionamiento.component';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'agregarPatente', component: AgregarPatenteComponent },
  { path: 'automovilistas', component: AutomovilistasComponent },
  { path: 'crearAutomovilista', component: CrearAutomovilistaComponent },
  { path: 'iniciarEstacionamiento', component: IniciarEstacionamientoComponent },
  { path: 'prueba', component: PruebaComponent },
  { path: 'login', component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
