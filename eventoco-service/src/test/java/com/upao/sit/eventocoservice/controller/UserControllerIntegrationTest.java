package com.upao.sit.eventocoservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upao.sit.eventocoservice.model.dto.UserRequestDTO;
import com.upao.sit.eventocoservice.model.dto.UserResponseDTO;
import com.upao.sit.eventocoservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    //test/visualizar-datos-del-usuario
    @Test
    public void testGetAllUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetUserById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", 14L))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetUserByUsername() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/username/{username}", "testing"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetUserByEmail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/email/{email}", "nuevousuario@example.com"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
