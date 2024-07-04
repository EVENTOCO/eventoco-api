package com.upao.sit.eventocoservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityResponseDTO {
    private Long id;
    private String nombre;
    private Boolean estado;
    private String responsable;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String etapa;
}
