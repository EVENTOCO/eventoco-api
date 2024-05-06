package com.upao.sit.eventocoservice.controller;

public class EventController {
}
@DeleteMapping("/eventos/{eventoId}")
public ResponseEntity<Void> eliminarEvento(@PathVariable Long eventoId) {
    eventoService.eliminarEvento(eventoId);
    return ResponseEntity.noContent().build();
}
