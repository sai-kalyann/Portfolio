package com.portfolio.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String role;
}
