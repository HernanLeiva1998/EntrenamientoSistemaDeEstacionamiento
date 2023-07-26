import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LoginInterface } from '../interfaces/login-interface';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent {
  telefono?: string;
  loginData: LoginInterface = { telefono: "", contrasena: "" };

  constructor(private service: LoginService) { }

  logear(telefono: string, contrasena: string) {
    this.loginData = { telefono: telefono, contrasena: contrasena }
    this.service.login(this.loginData).subscribe(a =>
      localStorage.setItem('telefono', a.telefono)
    )
  }

  logout() {
    localStorage.removeItem("telefono");
  }


}
