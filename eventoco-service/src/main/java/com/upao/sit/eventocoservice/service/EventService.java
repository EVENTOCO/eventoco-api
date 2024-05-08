package com.upao.sit.eventocoservice.service;

import com.upao.sit.eventocoservice.mapper.EventMapper;
import com.upao.sit.eventocoservice.repository.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final EventMapper mapper;

    public EventService(EventRepository eventRepository, EventMapper mapper) {
        this.eventRepository = eventRepository;
        this.mapper = mapper;
    }
}
