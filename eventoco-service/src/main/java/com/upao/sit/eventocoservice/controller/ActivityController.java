package com.upao.sit.eventocoservice.controller;

import com.upao.sit.eventocoservice.model.dto.ActivityRequestDTO;
import com.upao.sit.eventocoservice.model.dto.ActivityResponseDTO;
import com.upao.sit.eventocoservice.service.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/activities")
@AllArgsConstructor
public class ActivityController {
    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponseDTO> createActivity(@RequestBody ActivityRequestDTO activityRequestDTO) {
        return ResponseEntity.ok(activityService.createActivity(activityRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<ActivityResponseDTO>> getAllActivities() {
        return ResponseEntity.ok(activityService.getAllActivities());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityResponseDTO> getActivityById(@PathVariable Long id) {
        return ResponseEntity.ok(activityService.getActivityById(id));
    }

    @GetMapping("/date")
    public ResponseEntity<List<ActivityResponseDTO>> getActivitiesByDate(@RequestParam LocalDate fechaInicio, @RequestParam LocalDate fechaFin) {
        return ResponseEntity.ok(activityService.getActivitiesByDate(fechaInicio, fechaFin));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityResponseDTO> updateActivity(@PathVariable Long id, @RequestBody ActivityResponseDTO activityResponseDTO) {
        return ResponseEntity.ok(activityService.updateActivity(id, activityResponseDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        activityService.deleteActivity(id);
        return ResponseEntity.noContent().build();
    }
}
