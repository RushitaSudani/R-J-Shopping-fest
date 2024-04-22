package org.technous.validation.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterDto {
    private  String username;
    private  String email;
    private  long phone_no;
    private  String password;
    private String role;
}
