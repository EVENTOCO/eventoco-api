package com.upao.sit.eventocoservice.controller;


import com.upao.sit.eventocoservice.model.dto.EventResponseDTO;
import com.upao.sit.eventocoservice.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/important/{id}")
    public ResponseEntity<EventResponseDTO> changeEventImportant(Long id) {
        eventService.changeEventImportant(id);
        return ResponseEntity.ok().build();
    }

}
