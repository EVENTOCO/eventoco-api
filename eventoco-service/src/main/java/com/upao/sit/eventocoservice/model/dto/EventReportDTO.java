package com.upao.sit.eventocoservice.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class EventReportDTO {
    private String title;
    private LocalDateTime date;
    private String place;
    private String aproxNumParticipants;
}
