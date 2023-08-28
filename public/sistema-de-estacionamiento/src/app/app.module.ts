import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AutomovilistasComponent } from './components/automovilistas/automovilistas.component';
import { CrearAutomovilistaComponent } from './components/crear-automovilista/crear-automovilista.component';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { AgregarPatenteComponent } from './components/agregar-patente/agregar-patente.component';
import { PatentesComponent } from './components/patentes/patentes.component';
import { EstacionamientoComponent } from './components/estacionamiento/estacionamiento.component';
import { MensajeComponent } from './components/mensaje/mensaje.component';

@NgModule({
  declarations: [
    AppComponent,
    AutomovilistasComponent,
    CrearAutomovilistaComponent,
    LoginComponent,
    DashboardComponent,
    AgregarPatenteComponent,
    PatentesComponent,
    EstacionamientoComponent,
    MensajeComponent
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
