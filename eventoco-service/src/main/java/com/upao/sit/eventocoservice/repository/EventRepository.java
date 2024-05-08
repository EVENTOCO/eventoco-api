package com.upao.sit.eventocoservice.repository;

import com.upao.sit.eventocoservice.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> findByDate(LocalDateTime date);
    Optional<Event> findByTitle(String title);
    Optional<Event> findByTag(String tag);

}

