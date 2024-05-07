package com.upao.sit.eventocoservice.controller;


import com.upao.sit.eventocoservice.model.dto.EventRequestDTO;
import com.upao.sit.eventocoservice.model.dto.EventResponseDTO;
import com.upao.sit.eventocoservice.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PutMapping("/event/{id}")
    public ResponseEntity<EventResponseDTO> updateEvent(@PathVariable Long id, @RequestBody EventRequestDTO eventDTO) {
        EventResponseDTO updateEvent = eventService.updateEvent(id, eventDTO);
        return new ResponseEntity<>(updateEvent, HttpStatus.OK);
    }
}
