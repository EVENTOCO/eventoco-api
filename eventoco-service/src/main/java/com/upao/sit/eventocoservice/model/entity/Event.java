package com.upao.sit.eventocoservice.model.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email_user")
    private User email;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "place", nullable = false)
    private String place;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "description_service")
    private String descriptionService;

    @Column(name = "tag")
    private String tag;

    @Column(name = "is_important")
    private boolean isImportant;

    @Column(name = "aprox_num_participants")
    private String aproxNumParticipants;
}
