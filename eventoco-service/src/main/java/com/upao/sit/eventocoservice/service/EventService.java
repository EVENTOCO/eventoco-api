package com.upao.sit.eventocoservice.service;

import com.upao.sit.eventocoservice.exception.ResourceNotFoundException;
import com.upao.sit.eventocoservice.mapper.EventMapper;
import com.upao.sit.eventocoservice.model.dto.EventResponseDTO;
import com.upao.sit.eventocoservice.model.entity.Event;
import com.upao.sit.eventocoservice.repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final EventMapper mapper;

    public EventService(EventRepository eventRepository, EventMapper mapper) {
        this.eventRepository = eventRepository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public EventResponseDTO changeEventImportant(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Evento "+id+" no encontrado"));
        event.setImportant(true);
        eventRepository.save(event);
        return mapper.convertToDTO(event);
    }
}
