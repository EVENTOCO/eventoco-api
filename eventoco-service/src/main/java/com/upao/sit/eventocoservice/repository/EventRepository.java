package com.upao.sit.eventocoservice.repository;

import com.upao.sit.eventocoservice.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findById(Long id);
}
