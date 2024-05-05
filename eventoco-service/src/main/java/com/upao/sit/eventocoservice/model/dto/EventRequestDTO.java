package com.upao.sit.eventocoservice.model.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequestDTO {

    @NotBlank(message = "El título no puede estar vacío")
    private String title;

    @NotBlank(message = "La fecha y hora no puede estar vacía")
    @Future(message = "La fecha y hora no puede ser anterior a la actual")
    private LocalDateTime date;

    @NotBlank(message = "El lugar del evento no puede estar vacío")
    private String place;

    @NotBlank(message = "La descripción no puede estar vacía")
    private String description;

}
