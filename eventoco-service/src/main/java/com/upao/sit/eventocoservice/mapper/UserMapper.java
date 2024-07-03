package com.upao.sit.eventocoservice.mapper;

import com.upao.sit.eventocoservice.model.dto.UserRequestDTO;
import com.upao.sit.eventocoservice.model.dto.UserResponseDTO;
import com.upao.sit.eventocoservice.model.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import java.util.List;

@Component
@AllArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;

    public User convertToEntity(UserRequestDTO userRequestDTO){
        return modelMapper.map(userRequestDTO, User.class);
    }

    public UserResponseDTO convertToDTO(User user){
        return modelMapper.map(user, UserResponseDTO.class);
    }

    public List<UserResponseDTO> convertToListDTO(List<User> users){
        return users.stream()
                .map(this::convertToDTO)
                .toList();
    }
}
