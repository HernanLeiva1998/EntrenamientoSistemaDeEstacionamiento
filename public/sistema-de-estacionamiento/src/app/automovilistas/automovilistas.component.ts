import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-automovilistas',
  templateUrl: './automovilistas.component.html',
  styleUrls: ['./automovilistas.component.css']
})
export class AutomovilistasComponent implements OnInit {
  automovilistas: any[] = [];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get<any[]>('http://localhost:8080/automovilistas').subscribe(
      automovilistas => {
        this.automovilistas = automovilistas;
      },
      error => {
        console.log('Error al obtener los automovilistas:', error);
      }
    );
  }

}
