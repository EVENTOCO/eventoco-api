package com.upao.sit.eventocoservice.service;

public class EventService {
}
public void eliminarEvento(Long eventoId) {
    Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new ResourceNotFoundException("Evento no encontrado"));

    eventoRepository.delete(evento);
}
