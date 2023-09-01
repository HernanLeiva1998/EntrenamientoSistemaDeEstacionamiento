import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AutomovilistasComponent } from './components/automovilistas/automovilistas.component';
import { LoginComponent } from './components/login/login.component';
import { CrearAutomovilistaComponent } from './components/crear-automovilista/crear-automovilista.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { AgregarPatenteComponent } from './components/agregar-patente/agregar-patente.component';
import { EstacionamientoComponent } from './components/estacionamiento/estacionamiento.component';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'agregarPatente', component: AgregarPatenteComponent },
  { path: 'automovilistas', component: AutomovilistasComponent },
  { path: 'crearAutomovilista', component: CrearAutomovilistaComponent },
  { path: 'iniciarEstacionamiento', component: EstacionamientoComponent },
  { path: 'login', component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
