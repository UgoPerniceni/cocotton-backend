package fr.esgi.cocotton.application.authentication.dto;


import fr.esgi.cocotton.infrastructure.common.annotation.StrongPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class RegisterDTO {

    private final String firstName;

    private final String lastName;

    @Size(min = 6, max = 30)
    private final String username;

    @Email
    private final String email;

    @StrongPassword
    private final String password;

    private final String gender;

    @Past
    private final LocalDate birthDate;

    public RegisterDTO(String firstName, String lastName, String username, String email, String password, String gender, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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

    public String getGender() {
        return gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
