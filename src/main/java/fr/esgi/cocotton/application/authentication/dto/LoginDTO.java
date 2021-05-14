package fr.esgi.cocotton.application.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
public class LoginDTO {
    private String username;
    private String password;
}
