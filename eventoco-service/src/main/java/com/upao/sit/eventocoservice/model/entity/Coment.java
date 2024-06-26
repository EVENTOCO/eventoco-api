
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
@Table(name = "coments")
public class Coment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "created_at") // fecha de creacion
    private LocalDate created_at;
    @Column(name = "updated_at") // fecha de actualizacion
    private LocalDate updated_at;
    @JoinColumn(name = "user_id")
    private String user_id;
    @JoinColumn(name = "event_id")
    private String event_id;
}
