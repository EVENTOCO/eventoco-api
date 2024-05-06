package com.upao.sit.eventocoservice.service;

public class EventService {
}
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Evento } from './evento.model';

@Injectable({
  providedIn: 'root'
})
export class EventoService {
  private apiUrl = '/api/eventos';

  constructor(private http: HttpClient) { }

  getEventos(): Observable<Evento[]> {
    return this.http.get<Evento[]>(this.apiUrl);
  }

  eliminarEvento(eventoId: number): Observable<void> {
    const url = `${this.apiUrl}/${eventoId}`;
    return this.http.delete<void>(url);
  }
}
