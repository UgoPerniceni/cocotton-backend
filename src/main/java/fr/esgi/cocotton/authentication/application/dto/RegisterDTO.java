package fr.esgi.cocotton.authentication.application.dto;

import fr.esgi.cocotton.common.annotation.StrongPassword;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
public class RegisterDTO {

    private final String lastName;

    private final String firstName;

    @Size(min = 6, max = 30)
    private final String username;

    @Email
    private final String email;

    @StrongPassword
    private final String password;

    @Past
    private final LocalDate birthDate;

    public RegisterDTO(String lastName, String firstName, String username, String email, String password, LocalDate birthDate) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
