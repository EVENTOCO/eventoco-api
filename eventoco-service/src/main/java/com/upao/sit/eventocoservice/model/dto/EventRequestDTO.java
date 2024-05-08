package com.upao.sit.eventocoservice.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequestDTO {
    @NotBlank(message = "El título no puede estar vacío")
    private String title;

    @Future(message = "La fecha y hora no puede ser anterior a la actual")
    private LocalDateTime date;

    @NotBlank(message = "El lugar del evento no puede estar vacío")
    private String place;

    @NotBlank(message = "La descripción no puede estar vacía")
    private String description;

    private String descriptionService;
    private String tag;
    private boolean isImportant;
    private String aproxNumParticipants;
    private Long email_user;
}
