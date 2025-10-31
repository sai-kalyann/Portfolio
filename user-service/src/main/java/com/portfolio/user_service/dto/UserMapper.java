package com.portfolio.user_service.dto;

import com.portfolio.user_service.model.User;

public class UserMapper {
   public static User toEntity(UserRequestDTO dto) {
       return User.builder()
               .firstName(dto.getFirstName())
               .lastName(dto.getLastName())
               .email(dto.getEmail())
               .password(dto.getPassword())
               .phone(dto.getPhone())
               .role(dto.getRole())
               .build();
   }
   public static UserResponseDTO toResponseDTO(User user) {
       return UserResponseDTO.builder()
               .id(user.getId())
               .firstName(user.getFirstName())
               .lastName(user.getLastName())
               .email(user.getEmail())
               .phone(user.getPhone())
               .role(user.getRole())
               .build();
   }
}
