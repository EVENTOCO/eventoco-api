package com.upao.sit.eventocoservice.service;

import com.upao.sit.eventocoservice.exception.ResourceNotFoundException;
import com.upao.sit.eventocoservice.mapper.EventMapper;
import com.upao.sit.eventocoservice.model.dto.EventRequestDTO;
import com.upao.sit.eventocoservice.model.dto.EventResponseDTO;
import com.upao.sit.eventocoservice.model.entity.Event;
import com.upao.sit.eventocoservice.repository.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper mapper;

    public EventService(EventRepository eventRepository, EventMapper mapper) {
        this.eventRepository = eventRepository;
        this.mapper = mapper;
    }

    @Transactional
    public EventResponseDTO updateEvent(Long id, EventRequestDTO eventRequestDTO) {
        Event event = eventRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Error: " + "Evento(" +id+ ") no encontrado"));
        event.setTitle(eventRequestDTO.getTitle());
        event.setDate(eventRequestDTO.getDate());
        event.setPlace(eventRequestDTO.getPlace());
        event.setDescription(eventRequestDTO.getDescription());
        event = eventRepository.save(event);
        return mapper.convertToDTO(event);
    }
}
