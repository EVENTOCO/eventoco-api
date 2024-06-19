package com.upao.sit.eventocoservice.controller;

import com.upao.sit.eventocoservice.model.dto.VideoTutorialDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tutorial")
public class VideoTutorialController {

    @GetMapping("/video")
    public VideoTutorialDTO getVideoTutorial() {
        String videoUrl = "https://www.example.com/video-tutorial"; // URL del video tutorial
        return new VideoTutorialDTO(videoUrl);
    }
}
