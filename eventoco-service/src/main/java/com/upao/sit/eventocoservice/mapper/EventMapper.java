package com.upao.sit.eventocoservice.mapper;

import com.upao.sit.eventocoservice.model.dto.EventResponseDTO;
import com.upao.sit.eventocoservice.model.entity.Event;
import com.upao.sit.eventocoservice.model.dto.EventRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import java.util.List;

@Component
@AllArgsConstructor
public class EventMapper {
    private final ModelMapper modelMapper;

    public Event convertToEntity(EventRequestDTO eventRequestDTO) {
        return modelMapper.map(eventRequestDTO, Event.class);
    }
    public EventResponseDTO convertToDTO(Event event) {
        return modelMapper.map(event, EventResponseDTO.class);
    }
    public List<EventResponseDTO> convertToListDTO(List<Event> events) {
        return events.stream()
                .map(this::convertToDTO)
                .toList();
    }

}

