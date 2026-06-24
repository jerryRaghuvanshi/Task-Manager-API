package com.example.taskmanager.mapper;


import com.example.taskmanager.dto.UserRequestDTO;
import com.example.taskmanager.dto.UserResponseDTO;
import com.example.taskmanager.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")

public interface UserMapper {

    User toEntity(UserRequestDTO userRequestDTO);

    UserResponseDTO toResponseDTO(User user);
    List<UserResponseDTO> toResponseDTOs(List<User> users);

}
