import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PruebaComponent } from './prueba/prueba.component';
import { AutomovilistasComponent } from './automovilistas/automovilistas.component';
import { CrearAutomovilistaComponent } from './crear-automovilista/crear-automovilista.component';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AgregarPatenteComponent } from './agregar-patente/agregar-patente.component';
import { PatentesComponent } from './patentes/patentes.component';
import { CuentaCorrienteComponent } from './cuenta-corriente/cuenta-corriente.component';
import { IniciarEstacionamientoComponent } from './iniciar-estacionamiento/iniciar-estacionamiento.component';
import { EstacionamientoActivoComponent } from './estacionamiento-activo/estacionamiento-activo.component';

@NgModule({
  declarations: [
    AppComponent,
    PruebaComponent,
    AutomovilistasComponent,
    CrearAutomovilistaComponent,
    LoginComponent,
    DashboardComponent,
    AgregarPatenteComponent,
    PatentesComponent,
    CuentaCorrienteComponent,
    IniciarEstacionamientoComponent,
    EstacionamientoActivoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
