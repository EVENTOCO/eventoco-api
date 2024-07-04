package com.upao.sit.eventocoservice.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityRequestDTO {
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    private Boolean estado;
    @NotBlank(message = "El responsable no puede estar vacío")
    private String responsable;
    @NotBlank(message = "La fecha de inicio no puede estar vacío")
    private LocalDate fechaInicio;
    @NotBlank(message = "La fecha de fin no puede estar vacío")
    private LocalDate fechaFin;
    @NotBlank(message = "La etapa no puede estar vacío")
    private String etapa;
    @NotBlank(message = "El evento no puede estar vacío")
    private Long eventoId;
}
