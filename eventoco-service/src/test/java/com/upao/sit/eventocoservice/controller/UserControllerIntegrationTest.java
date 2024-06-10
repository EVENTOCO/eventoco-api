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

    //test/crear-usuario
    @Test
    public void testCreateUser() throws Exception {
        UserRequestDTO newUser = new UserRequestDTO();
        newUser.setUsername("yetanotheruser");
        newUser.setPassword("password");
        newUser.setEmail("yetanotheremail@example.com");
        newUser.setPhone("29182472");
        newUser.setBirthday(LocalDate.of(2000, 1, 1));

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    //test/actualizar-usuario
    @Test
    public void testUpdateUser() throws Exception {
        UserRequestDTO updatedUser = new UserRequestDTO();
        updatedUser.setUsername("testingchanges");
        updatedUser.setPassword("newpassword");
        updatedUser.setEmail("updateduser@example.com");
        updatedUser.setPhone("0987654321");
        updatedUser.setBirthday(LocalDate.of(1990, 1, 1));

        mockMvc.perform(MockMvcRequestBuilders.put("/users/{id}", 2L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUser)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
