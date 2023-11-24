import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private apiUrl = 'http://localhost:8080/api';
  constructor(private http: HttpClient) { }

  getJuegos(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/getJuegos`);
  }

  getCountPuntajesTotales(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/getCountPuntajesTotales`);
  }

  // Método para obtener el conteo de puntajes por juego
  getCountPuntajesPorJuego(nombreJuego: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/getCountPuntajesPorJuego?nombreJuego=${nombreJuego}`);
  }

  // Método para obtener puntajes totales ordenados
  getPuntajesTotalesOrdenados(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/getPuntajesTotalesOrdenados`);
  }
 
  // Método para obtener puntajes por juego ordenados
  getPuntajesPorJuegoOrdenados(nombreJuego: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/getPuntajesPorJuegoOrdenados?nombreJuego=${nombreJuego}`);
  }
 
  // Método para obtener puntajes totales ordenados con límite
  getPuntajesTotalesOrdenadosLimit(limite: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/getPuntajesTotalesOrdenadosLimit?limite=${limite}`);
  }
 
  // Método para obtener puntajes por juego ordenados con límite
  getPuntajesPorJuegoOrdenadosLimit(nombreJuego: string, limite: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/getPuntajesPorJuegoOrdenadosLimit?nombreJuego=${nombreJuego}&limite=${limite}`);
  }
 
  // Método para obtener puntajes totales ordenados con límite y offset
  getPuntajesTotalesOrdenadosLimitOffset(limite: number, cantDescartados: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/getPuntajesTotalesOrdenadosLimitOffset?limite=${limite}&cantDescartados=${cantDescartados}`);
  }
 
  // Método para obtener puntajes por juego ordenados con límite y offset
  getPuntajesPorJuegoOrdenadosLimitOffset(nombreJuego: string, limite: number, cantDescartados: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/getPuntajesPorJuegoOrdenadosLimitOffset?nombreJuego=${nombreJuego}&limite=${limite}&cantDescartados=${cantDescartados}`);
  }
  
}
