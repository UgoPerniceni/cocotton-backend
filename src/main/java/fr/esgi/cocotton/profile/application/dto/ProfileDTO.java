package fr.esgi.cocotton.profile.application.dto;

import fr.esgi.cocotton.profile.domain.Profile;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class ProfileDTO {

    private final String lastName;

    private final String firstName;

    @Size(min = 6, max = 30)
    private final String username;

    @Email
    private final String email;

    @Past
    private final LocalDate birthDate;

    public ProfileDTO(Profile profile) {
        this.lastName = profile.getLastName();
        this.firstName = profile.getFirstName();
        this.username = profile.getUsername();
        this.email = profile.getEmail();
        this.birthDate = profile.getBirthDate();
    }

    public ProfileDTO(String lastName, String firstName, String username, String email, LocalDate birthDate) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.username = username;
        this.email = email;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
