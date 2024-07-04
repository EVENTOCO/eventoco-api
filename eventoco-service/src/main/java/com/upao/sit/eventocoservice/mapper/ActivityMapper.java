package com.upao.sit.eventocoservice.mapper;

import com.upao.sit.eventocoservice.model.dto.ActivityRequestDTO;
import com.upao.sit.eventocoservice.model.dto.ActivityResponseDTO;
import com.upao.sit.eventocoservice.model.entity.Activity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ActivityMapper {
    private final ModelMapper modelMapper;

    public Activity convertToEntity(ActivityRequestDTO activityDTO) {
        return modelMapper.map(activityDTO, Activity.class);
    }

    public ActivityResponseDTO convertToDTO(Activity activity) {
        return modelMapper.map(activity, ActivityResponseDTO.class);
    }

    public List<ActivityResponseDTO> convertToListDTO(List<Activity> activities) {
        return activities.stream()
                .map(this::convertToDTO)
                .toList();
    }
}
