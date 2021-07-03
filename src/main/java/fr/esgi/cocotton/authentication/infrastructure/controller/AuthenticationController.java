package fr.esgi.cocotton.authentication.infrastructure.controller;

import fr.esgi.cocotton.authentication.application.Login;
import fr.esgi.cocotton.authentication.application.Register;
import fr.esgi.cocotton.authentication.application.dto.LoginDTO;
import fr.esgi.cocotton.authentication.application.dto.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final Register register;
    private final Login login;

    @Autowired
    public AuthenticationController(Register register, Login login){
        this.register = register;
        this.login = login;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDTO registerDTO) {
        URI uri = register.execute(registerDTO);
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        HttpHeaders headers = login.execute(loginDTO);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
