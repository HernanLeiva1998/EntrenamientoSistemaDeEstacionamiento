import { Component,OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LoginInterface } from '../interfaces/login-interface';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent {
  telefono?: string;
  loginData: LoginInterface = {telefono:"", contrasena:""};
  
  logear(telefono: string, contrasena: string){
    this.loginData= {telefono:telefono, contrasena:contrasena}
    }


}
