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
        
        if (eventoDto == null) {
            return ResponseEntity.badRequest().build();
        }
        
        
        if (!eventoId.equals(eventoDto.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        
        Evento eventoModificado = eventoService.modificarEvento(eventoId, eventoDto);
        return ResponseEntity.ok(eventoModificado);
    }

    @DeleteMapping("/{eventoId}")
    public ResponseEntity<Void> eliminarEvento(@PathVariable Long eventoId) {
        eventoService.eliminarEvento(eventoId);
        return ResponseEntity.noContent().build();
    }
}
