package com.upao.sit.eventocoservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VideoTutorialController.class)
public class VideoTutorialControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetVideoTutorial() throws Exception {
        mockMvc.perform(get("/api/tutorial/video"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.videoUrl").value("https://www.example.com/video-tutorial"));
    }
}