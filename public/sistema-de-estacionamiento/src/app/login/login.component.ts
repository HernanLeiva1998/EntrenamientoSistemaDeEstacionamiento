import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LoginInterface } from '../interfaces/login-interface';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';
import jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent {
  telefono?: string;
  loginData: LoginInterface = { telefono: "", contrasena: "" };
  mensaje?: string;

  constructor(private service: LoginService, private router: Router) { }

  logear(telefono: string, contrasena: string) {
    this.loginData = { telefono: telefono, contrasena: contrasena }
    this.service.login(this.loginData).subscribe(
      {
        next: (t) => {
          localStorage.setItem('token', t.token)
          const decodedToken: any = jwt_decode(t.token)
          localStorage.setItem('telefono', decodedToken.sub)
          console.log(decodedToken);
          this.router.navigate(['/dashboard'])
        },
        error: (e) => this.mensaje = e
      })
  }

  logout() {
    this.service.logout()
  }


}
