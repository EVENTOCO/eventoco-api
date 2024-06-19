package com.upao.sit.eventocoservice.service;

import com.upao.sit.eventocoservice.exception.InvalidEventDateException;
import com.upao.sit.eventocoservice.exception.ResourceNotFoundException;
import com.upao.sit.eventocoservice.mapper.EventMapper;
import com.upao.sit.eventocoservice.model.dto.EventRequestDTO;
import com.upao.sit.eventocoservice.model.dto.EventResponseDTO;
import com.upao.sit.eventocoservice.model.dto.UserRequestDTO;
import com.upao.sit.eventocoservice.model.dto.UserResponseDTO;
import com.upao.sit.eventocoservice.model.entity.Event;
import com.upao.sit.eventocoservice.model.entity.User;
import com.upao.sit.eventocoservice.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {
    @Mock
    private EventRepository eventRepository;
    @Mock
    private EventMapper eventMapper;
    @InjectMocks
    private EventService eventService;

    @Test
    public void testGetAllEvents() {
        //Arrange
        Event event_1 = new Event();
        event_1.setId(1L);
        Event event_2 = new Event();
        event_2.setId(2L);
        Event event_3 = new Event();
        event_3.setId(3L);
        List<Event> events = Arrays.asList(event_1, event_2, event_3);

        //Simular el comportaiento del eventRepository --> List<Event> events = eventRepository.findAll();
        when(eventRepository.findAll()).thenReturn(events);

        EventResponseDTO responseDTO_01 = new EventResponseDTO();
        responseDTO_01.setId(1L);
        EventResponseDTO responseDTO_02 = new EventResponseDTO();
        responseDTO_01.setId(2L);
        EventResponseDTO responseDTO_03 = new EventResponseDTO();
        responseDTO_01.setId(3L);
        List<EventResponseDTO> expectedResponseDTOs = Arrays.asList(responseDTO_01, responseDTO_02, responseDTO_03);
        //Simular el comportamiento del eventMapper.convertToListDTO(events)
        when(eventMapper.convertToListDTO(events)).thenReturn(expectedResponseDTOs);

        //Act: Aca se realiza la prueba del metodo getAllEvents()
        List<EventResponseDTO> eventResult = eventService.getAllEvents();

        //Assert
        assertEquals(expectedResponseDTOs.size(), eventResult.size());
    }

    @Test
    public void testGetEventById_ExistingId() {
        //Arrange
        Long id = 1L;
        Event event = new Event();
        event.setId(id);
        when(eventRepository.findById(id)).thenReturn(Optional.of(event));

        EventResponseDTO responseDTO = new EventResponseDTO();
        responseDTO.setId(event.getId());
        when(eventMapper.convertToDTO(event)).thenReturn(responseDTO);

        //Act
        EventResponseDTO result = eventService.getEventById(id);

        //Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    public void testGetEventById_NonExistingId() {
        //Arrange
        Long id = 99L;
        when(eventRepository.findById(id)).thenReturn(Optional.empty());

        //Act and Assert
        assertThrows(ResourceNotFoundException.class,
                () -> eventService.getEventById(id));
    }

    @Test
    public void testCreateEvent_Successful() {
        // Arrange
        EventRequestDTO validEventRequest = new EventRequestDTO();
        validEventRequest.setTitle("Cumpleaños");
        validEventRequest.setDate(LocalDateTime.of(2024, 8, 8, 11, 30));
        validEventRequest.setPlace("Trujillo");
        validEventRequest.setDescription("Cumple 22");
        validEventRequest.setDescriptionService("Decoracion y DJ");
        validEventRequest.setTag("Cumpleaños");
        validEventRequest.setImportant(true);
        validEventRequest.setAproxNumParticipants("45 participantes");

        Event event = new Event();
        event.setId(1L);
        event.setTitle("Cumpleaños");
        event.setDate(LocalDateTime.of(2024, 8, 8, 11, 30));
        event.setPlace("Trujillo");
        event.setDescription("Cumple 22");
        event.setDescriptionService("Decoracion y DJ");
        event.setTag("Cumpleaños");
        event.setImportant(true);
        event.setAproxNumParticipants("45 participantes");

        Event savedEvent = new Event();
        savedEvent.setId(1L);
        savedEvent.setTitle("Cumpleaños");
        savedEvent.setDate(LocalDateTime.of(2024, 8, 8, 11, 30));
        savedEvent.setPlace("Trujillo");
        savedEvent.setDescription("Cumple 22");
        savedEvent.setDescriptionService("Decoracion y DJ");
        savedEvent.setTag("Cumpleaños");
        savedEvent.setImportant(true);
        savedEvent.setAproxNumParticipants("45 participantes");

        EventResponseDTO responseDTO = new EventResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setTitle("Cumpleaños");
        responseDTO.setDate(LocalDateTime.of(2024, 8, 8, 11, 30));
        responseDTO.setPlace("Trujillo");
        responseDTO.setDescription("Cumple 22");
        responseDTO.setDescriptionService("Decoracion y DJ");
        responseDTO.setTag("Cumpleaños");
        responseDTO.setImportant(true);
        responseDTO.setAproxNumParticipants("45 participantes");

        when(eventMapper.convertToEntity(validEventRequest)).thenReturn(event);
        when(eventRepository.save(event)).thenReturn(savedEvent);
        when(eventMapper.convertToDTO(savedEvent)).thenReturn(responseDTO);

        // Act
        EventResponseDTO result = eventService.createEvent(validEventRequest);

        // Assert
        assertNotNull(result);
        assertEquals(responseDTO.getId(), result.getId());
        assertEquals(responseDTO.getTitle(), result.getTitle());
    }

    @Test
    public void testCreateEvent_FailDueToMissingFields() {
        // Arrange
        EventRequestDTO validEventRequest = new EventRequestDTO();
        validEventRequest.setTitle(null);
        validEventRequest.setDate(LocalDateTime.of(2024, 8, 8, 11, 30));
        validEventRequest.setPlace("Trujillo");
        validEventRequest.setDescription("Cumple 22");
        validEventRequest.setDescriptionService("Decoracion y DJ");
        validEventRequest.setTag("Cumpleaños");
        validEventRequest.setImportant(true);
        validEventRequest.setAproxNumParticipants("45 participantes");

        // Act
        EventResponseDTO result = eventService.createEvent(validEventRequest);

        // Assert
        assertNull(result);
    }

    @Test
    public void testCreateEvent_FailDueToOldDate() {
        // Arrange
        EventRequestDTO validEventRequest = new EventRequestDTO();
        validEventRequest.setTitle("Cumpleaños");
        validEventRequest.setDate(LocalDateTime.of(2023, 8, 8, 11, 30));
        validEventRequest.setPlace("Trujillo");
        validEventRequest.setDescription("Cumple 22");
        validEventRequest.setDescriptionService("Decoracion y DJ");
        validEventRequest.setTag("Cumpleaños");
        validEventRequest.setImportant(true);
        validEventRequest.setAproxNumParticipants("45 participantes");

        // Act & Assert
        assertThrows(InvalidEventDateException.class, () -> {
            eventService.createEvent(validEventRequest);
        });
    }
    @Test
    public void testDeleteEvent_Successful(){
        //Arrange
        Long eventId = 1L;
        Event event = new Event(eventId,new User(),"Cumpleaños", LocalDateTime.of(2024, 8, 8, 11,30),"Trujillo","Cumple 22","Decoracion y DJ", "Cumpleaños", true, "45 participantes");

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        doNothing().when(eventRepository).delete(event);

        //Act
        eventService.deleteEvent(eventId);

        //Assert
        verify(eventRepository, times(1)).findById(eventId);
        verify(eventRepository, times(1)).delete(event);

    }
}