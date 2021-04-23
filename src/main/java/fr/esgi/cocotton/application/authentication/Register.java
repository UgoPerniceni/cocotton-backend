package fr.esgi.cocotton.application.authentication;

import fr.esgi.cocotton.application.user.AddUser;
import fr.esgi.cocotton.domain.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
public class Register {

    private final AddUser addUser;

    @Autowired
    public Register(AddUser addUser){
        this.addUser = addUser;
    }

    public URI execute(User user){
        String id = addUser.execute(user);
        return ServletUriComponentsBuilder.fromPath("/api/users").path("/{id}").buildAndExpand(id).toUri();
    }
}
