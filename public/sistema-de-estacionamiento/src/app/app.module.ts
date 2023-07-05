import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PruebaComponent } from './prueba/prueba.component';
import { AutomovilistasComponent } from './automovilistas/automovilistas.component';
import { CrearAutomovilistaComponent } from './crear-automovilista/crear-automovilista.component';

@NgModule({
  declarations: [
    AppComponent,
    PruebaComponent,
    AutomovilistasComponent,
    CrearAutomovilistaComponent
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
