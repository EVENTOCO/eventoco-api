package com.upao.sit.eventocoservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upao.sit.eventocoservice.model.entity.Coment;
import com.upao.sit.eventocoservice.service.ComentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
public class ComentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ComentService comentService;

    //test/crear-comentario
    @Test
    public void testCrearComentario() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/coments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Coment(342830L, "Comentario de prueba", LocalDate.now(), LocalDate.now(), "1", "1"))))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
