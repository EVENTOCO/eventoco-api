package com.upao.sit.eventocoservice.controller;

import com.upao.sit.eventocoservice.model.dto.EventRequestDTO;
import com.upao.sit.eventocoservice.model.dto.EventResponseDTO;
import com.upao.sit.eventocoservice.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getAllEvents() {
        List<EventResponseDTO> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEventById(@PathVariable Long id) {
        EventResponseDTO event = eventService.getEventById(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<EventResponseDTO> getEventByDate(@PathVariable LocalDateTime date) {
        EventResponseDTO event = eventService.getEventByDate(date);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<EventResponseDTO> getEventByTitle(@PathVariable String title) {
        EventResponseDTO event = eventService.getEventByTitle(title);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @GetMapping("/tag/{tag}")
    public ResponseEntity<EventResponseDTO> getEventByTag(@PathVariable String tag) {
        EventResponseDTO event = eventService.getEventByTag(tag);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<EventResponseDTO> createEvent(@Validated @RequestBody
                                                        EventRequestDTO eventDTO) {
        EventResponseDTO createdEvent = eventService.createEvent(eventDTO);
        return new  ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventResponseDTO> updateEvent(@PathVariable Long id, @RequestBody EventRequestDTO eventDTO){
        EventResponseDTO updateEvent = eventService.updateEvent(id, eventDTO);
        return new ResponseEntity<>(updateEvent, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id){
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
