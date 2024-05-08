package com.upao.sit.eventocoservice.service;

import com.upao.sit.eventocoservice.exception.ResourceNotFoundException;
import com.upao.sit.eventocoservice.mapper.EventMapper;
import com.upao.sit.eventocoservice.model.dto.EventResponseDTO;
import com.upao.sit.eventocoservice.model.dto.EventRequestDTO;
import com.upao.sit.eventocoservice.model.entity.Event;
import com.upao.sit.eventocoservice.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Transactional(readOnly = true)
    public List<EventResponseDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return eventMapper.convertToListDTO(events);
    }

    @Transactional(readOnly = true)
    public EventResponseDTO getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento con el número "+id+ " no encontrado"));
        return eventMapper.convertToDTO(event);
    }

    @Transactional(readOnly = true)
    public EventResponseDTO getEventByTitle(String title) {
        Event event = eventRepository.findByTitle(title)
                .orElseThrow(() -> new ResourceNotFoundException("Evento con el titulo "+title+ " no encontrado"));
        return eventMapper.convertToDTO(event);
    }

    @Transactional(readOnly = true)
    public EventResponseDTO getEventByDate(LocalDateTime date) {
        Event event = eventRepository.findByDate(date)
                .orElseThrow(() -> new ResourceNotFoundException("Evento con fecha "+date+ " no encontrado"));
        return eventMapper.convertToDTO(event);
    }

    @Transactional(readOnly = true)
    public EventResponseDTO getEventByTag(String tag) {
        Event event = eventRepository.findByTag(tag)
                .orElseThrow(() -> new ResourceNotFoundException("Evento con etiqueta "+tag+ " no encontrado"));
        return eventMapper.convertToDTO(event);
    }

    @Transactional
    public EventResponseDTO createEvent(EventRequestDTO eventRequestDTO) {
        Event event = eventMapper.convertToEntity(eventRequestDTO);
        eventRepository.save(event);
        return eventMapper.convertToDTO(event);
    }

    @Transactional
    public EventResponseDTO updateEvent(Long id, EventRequestDTO eventRequestDTO) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento con el número "+id+ " no encontrado"));
        event.setTitle(eventRequestDTO.getTitle());
        event.setDate(eventRequestDTO.getDate());
        event.setPlace(eventRequestDTO.getPlace());
        event.setDescription(eventRequestDTO.getDescription());
        event.setDescriptionService(eventRequestDTO.getDescriptionService());
        event.setTag(eventRequestDTO.getTag());
        event.setImportant(eventRequestDTO.isImportant());
        event.setAproxNumParticipants(eventRequestDTO.getAproxNumParticipants());
        event = eventRepository.save(event);
        return eventMapper.convertToDTO(event);
    }

    @Transactional
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
