package org.technous.validation.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginResponseDto {
    Long id;
    String username;
    String email;
    String role;
    Long userId;


}
