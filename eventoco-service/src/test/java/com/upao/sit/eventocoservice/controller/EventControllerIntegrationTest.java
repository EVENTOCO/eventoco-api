package com.upao.sit.eventocoservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upao.sit.eventocoservice.model.dto.EventRequestDTO;
import com.upao.sit.eventocoservice.service.EventService;
import org.junit.jupiter.api.Test;
import org.springdoc.core.providers.ObjectMapperProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private EventService eventService;

    @Test
    public void testGetEventById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/events/{id}", 12L))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetEventByDate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/events/date/{date}", LocalDateTime.of(2024, 8, 8, 11, 30)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetEventByTitle() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/events/title/{title}", "Cumpleaños"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetEventByTag() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/events/tag/{tag}", "Cumpleaños"));
    }

    //test/crear-evento
    @Test
    public void testCreateEvent() throws Exception {
        EventRequestDTO newEvent = new EventRequestDTO();
        newEvent.setTitle("Cumpleaños");
        newEvent.setDate(LocalDateTime.of(2024, 8, 8, 11, 30));
        newEvent.setPlace("Trujillo");
        newEvent.setDescription("Cumple 22");
        newEvent.setDescriptionService("Decoracion y DJ");
        newEvent.setTag("Cumpleaños");
        newEvent.setImportant(true);
        newEvent.setAproxNumParticipants("45 participantes");

        mockMvc.perform(MockMvcRequestBuilders.post("/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newEvent)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testDeleteEvent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/events/{id}", 23L))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}