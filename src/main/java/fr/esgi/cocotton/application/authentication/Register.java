package fr.esgi.cocotton.application.authentication;

import fr.esgi.cocotton.application.authentication.dto.RegisterDTO;
import fr.esgi.cocotton.application.profile.AddProfile;
import fr.esgi.cocotton.domain.models.profile.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
public class Register {

    private final AddProfile addUser;

    @Autowired
    public Register(AddProfile addUser){
        this.addUser = addUser;
    }

    public URI execute(RegisterDTO registerDTO){
        String id = addUser.execute(new Profile(registerDTO.getFirstName(),
                registerDTO.getLastName(),
                registerDTO.getUsername(),
                registerDTO.getEmail(),
                registerDTO.getPassword(),
                registerDTO.getGender(),
                registerDTO.getBirthDate())
        );
        return ServletUriComponentsBuilder.fromPath("/api/users").path("/{id}").buildAndExpand(id).toUri();
    }
}
