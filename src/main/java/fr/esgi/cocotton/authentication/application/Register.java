package fr.esgi.cocotton.authentication.application;

import fr.esgi.cocotton.authentication.application.dto.RegisterDTO;
import fr.esgi.cocotton.profile.application.AddProfile;
import fr.esgi.cocotton.profile.domain.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
public class Register {

    private final AddProfile addProfile;

    @Autowired
    public Register(AddProfile addProfile) {
        this.addProfile = addProfile;
    }

    public URI execute(RegisterDTO registerDTO) {
        String id = addProfile.execute(new Profile
                (registerDTO.getLastName(),
                        registerDTO.getFirstName(),
                        registerDTO.getUsername(),
                        registerDTO.getEmail(),
                        registerDTO.getPassword(),
                        registerDTO.getBirthDate()));

        return ServletUriComponentsBuilder.fromPath("/profiles").path("/{id}").buildAndExpand(id).toUri();
    }
}
