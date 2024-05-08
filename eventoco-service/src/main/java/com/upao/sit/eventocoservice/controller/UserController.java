package com.upao.sit.eventocoservice.controller;

public class UserController {
}
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @PutMapping("/{eventoId}")
    public ResponseEntity<Evento> modificarEvento(@PathVariable Long eventoId,
                                                  @RequestBody EventoDto eventoDto) {
        // Validar entrada
        if (eventoDto == null) {
            return ResponseEntity.badRequest().build();
        }
        
        // Validar si el ID del evento coincide con el ID en el DTO
        if (!eventoId.equals(eventoDto.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        // Realizar la modificación del evento
        Evento eventoModificado = eventoService.modificarEvento(eventoId, eventoDto);
        return ResponseEntity.ok(eventoModificado);
    }

    @DeleteMapping("/{eventoId}")
    public ResponseEntity<Void> eliminarEvento(@PathVariable Long eventoId) {
        // Realizar la eliminación del evento
        eventoService.eliminarEvento(eventoId);
        return ResponseEntity.noContent().build();
    }
}
