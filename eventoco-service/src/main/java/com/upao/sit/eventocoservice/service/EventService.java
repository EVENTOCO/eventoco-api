package com.upao.sit.eventocoservice.service;

public class EventService {
}
// EventoService.java
@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public void eliminarEvento(Long eventoId) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new ResourceNotFoundException("Evento no encontrado"));

        // Verifica si el usuario autenticado tiene permisos para eliminar el evento
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated() || !evento.getCreador().equals(authentication.getName())) {
            throw new AccessDeniedException("No tienes permisos para eliminar este evento");
        }

        eventoRepository.delete(evento);
    }
}
