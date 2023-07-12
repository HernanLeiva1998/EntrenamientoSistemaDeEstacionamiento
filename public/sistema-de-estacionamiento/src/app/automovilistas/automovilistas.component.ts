import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Automovilista } from '../interfaces/automovilista';

@Component({
  selector: 'app-automovilistas',
  templateUrl: './automovilistas.component.html',
  styleUrls: ['./automovilistas.component.css']
})
export class AutomovilistasComponent implements OnInit {
  automovilistas: Automovilista[] = [];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get<Automovilista[]>('http://localhost:8080/automovilistas').subscribe(
      automovilistas => {
        this.automovilistas = automovilistas;
      },
      error => {
        console.log('Error al obtener los automovilistas:', error);
      }
    );
  }

}
