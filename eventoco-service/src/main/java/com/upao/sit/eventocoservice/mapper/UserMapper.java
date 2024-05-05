package com.upao.sit.eventocoservice.mapper;

import com.upao.sit.eventocoservice.model.dto.UserRequestDTO;
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

    public UserRequestDTO convertToDTO(User user){
        return modelMapper.map(user, UserRequestDTO.class);
    }

    public List<UserRequestDTO> convertToDTO(List<User> users){
        return users.stream()
                .map(this::convertToDTO)
                .toList();
    }
}
