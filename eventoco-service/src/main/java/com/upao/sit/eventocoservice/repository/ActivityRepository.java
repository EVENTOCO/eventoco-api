package com.upao.sit.eventocoservice.repository;

import com.upao.sit.eventocoservice.model.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByFechaInicio(LocalDate fechaInicio);
    List<Activity> findByFechaFin(LocalDate fechaFin);
    List<Activity> findByFechaInicioAndFechaFin(LocalDate fechaInicio, LocalDate fechaFin);
}
