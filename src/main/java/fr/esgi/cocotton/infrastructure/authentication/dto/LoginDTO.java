package fr.esgi.cocotton.infrastructure.authentication.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String username;
    private String password;
}