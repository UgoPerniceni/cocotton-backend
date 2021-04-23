package fr.esgi.cocotton.application.authentication.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LoginDTO {
    private String username;
    private String password;
}
