import { Component, OnInit } from '@angular/core';
import { ApiService } from './api.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'my-app';

  constructor(private apiService: ApiService) {  }

  limitPersonas: number = 5;
  juegos: any[] = [];
  jsonData: any[] = [];

  ngOnInit() {
    // Aquí deberías cargar tu JSON, por ejemplo, desde un servicio HTTP
    this.loadJuegos();
    this.loadJsonData();
  }

  loadJuegos(){
    this.apiService.getJuegos().subscribe(data => { 
      console.log(data);
      this.juegos = data;
      this.displayedJuegos = this.juegos.slice(0, this.cantItems);
    });
  }

  loadJsonData(){
    this.apiService.getPuntajesTotalesOrdenadosLimit(this.limitPersonas).subscribe(data => { 
      this.jsonData = data; 
      //this.jsonData.length;
    });
  }

  onJuegoClick(juego: string): void {
    if (juego == "Total"){
      this.loadJsonData();
    }
    else{
      this.apiService.getPuntajesPorJuegoOrdenadosLimit(juego, this.limitPersonas).subscribe(data => { 
        this.jsonData = data;
        console.log(data);
      });
    }
  }

  displayedJuegos: any[] = [];
  cantItems = 4;
  index = 0;
  
  scrollToolbar(direction: string) {
    //const cantItems = this.cantItems;
    if (direction === 'left') {
      if(this.index != 0) this.index--;
      //const startIndex = Math.max(0, this.juegos.indexOf(this.displayedJuegos[0]) - cantItems);
      //this.displayedJuegos = this.juegos.slice(startIndex, startIndex + 5);
    } else if (direction === 'right') {
      if(this.index < this.juegos.length/5) this.index++;
      //const endIndex = Math.min(this.juegos.length, this.juegos.indexOf(this.displayedJuegos[this.displayedJuegos.length - 1]) + cantItems);
      //this.displayedJuegos = this.juegos.slice(endIndex - 5, endIndex);
    }
    this.displayedJuegos = this.juegos.slice(this.index * this.cantItems, this.index * this.cantItems + this.cantItems);
  }
}