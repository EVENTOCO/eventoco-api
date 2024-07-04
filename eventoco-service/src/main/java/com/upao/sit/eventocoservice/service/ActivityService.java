package com.upao.sit.eventocoservice.service;

import com.upao.sit.eventocoservice.exception.ResourceNotFoundException;
import com.upao.sit.eventocoservice.mapper.ActivityMapper;
import com.upao.sit.eventocoservice.model.dto.ActivityRequestDTO;
import com.upao.sit.eventocoservice.model.dto.ActivityResponseDTO;
import com.upao.sit.eventocoservice.model.entity.Activity;
import com.upao.sit.eventocoservice.repository.ActivityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;

    @Transactional
    public ActivityResponseDTO createActivity(ActivityRequestDTO activityRequestDTO) {
        Activity activity = activityMapper.convertToEntity(activityRequestDTO);
        activityRepository.save(activity);
        return activityMapper.convertToDTO(activity);
    }

    @Transactional(readOnly = true)
    public List<ActivityResponseDTO> getAllActivities() {
        List<Activity> activities = activityRepository.findAll();
        return activityMapper.convertToListDTO(activities);
    }

    @Transactional(readOnly = true)
    public ActivityResponseDTO getActivityById(Long id) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actividad con el número "+id+ " no encontrada"));
        return activityMapper.convertToDTO(activity);
    }

    @Transactional(readOnly = true)
    public List<ActivityResponseDTO> getActivitiesByDate(LocalDate fechaInicio, LocalDate fechaFin) {
        List<Activity> activities = activityRepository.findByFechaInicioAndFechaFin(fechaInicio, fechaFin);
        return activityMapper.convertToListDTO(activities);
    }

    @Transactional
    public ActivityResponseDTO updateActivity(Long id, ActivityResponseDTO activityRequestDTO) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actividad con el número "+id+ " no encontrada"));
        activity.setNombre(activityRequestDTO.getNombre());
        activity.setEstado(activityRequestDTO.getEstado());
        activity.setResponsable(activityRequestDTO.getResponsable());
        activity.setFechaInicio(activityRequestDTO.getFechaInicio());
        activity.setFechaFin(activityRequestDTO.getFechaFin());
        activity.setEtapa(Activity.Etapa.valueOf(activityRequestDTO.getEtapa()));
        activityRepository.save(activity);
        return activityMapper.convertToDTO(activity);
    }

    @Transactional
    public void deleteActivity(Long id) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actividad con el número "+id+ " no encontrada"));
        activityRepository.delete(activity);
    }
}
