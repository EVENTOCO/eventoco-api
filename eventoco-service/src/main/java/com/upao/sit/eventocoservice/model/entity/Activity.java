package com.upao.sit.eventocoservice.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "activities")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Boolean estado;

    @Column(nullable = false)
    private String responsable;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private LocalDate fechaFin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Etapa etapa;
    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Event evento;

    public enum Etapa {
        ANTES_DEL_EVENTO,
        DURANTE_EL_EVENTO,
        DESPUES_DEL_EVENTO
    }
}
