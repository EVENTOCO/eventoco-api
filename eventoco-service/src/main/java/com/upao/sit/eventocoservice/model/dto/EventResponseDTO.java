package com.upao.sit.eventocoservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponseDTO {
    private Long id;
    private UserResponseDTO email;
    private String title;
    private LocalDateTime date;
    private String place;
    private String description;
    private String descriptionService;
    private String tag;
    private boolean isImportant;
    private String aproxNumParticipants;
}
