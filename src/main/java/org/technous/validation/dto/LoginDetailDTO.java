package org.technous.validation.dto;

import lombok.Data;

@Data
public class LoginDetailDTO {
    private Long userId;
    private String email;
    private String password;
    private String phoneNo;
    private String username;
    private String roleName;

    // Constructor
    public LoginDetailDTO(Long userId, String email, String password, String phoneNo, String username, String roleName) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.phoneNo = phoneNo;
        this.username = username;
        this.roleName = roleName;
    }
}
