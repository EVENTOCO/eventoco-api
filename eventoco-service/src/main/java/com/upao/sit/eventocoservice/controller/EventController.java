package com.upao.sit.eventocoservice.controller;

public class EventController {
}
@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @DeleteMapping("/{eventoId}")
    public ResponseEntity<Void> eliminarEvento(@PathVariable Long eventoId) {
        eventoService.eliminarEvento(eventoId);
        return ResponseEntity.noContent().build();
    }
}
