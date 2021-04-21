package fr.esgi.cocotton.infrastructure.authentication.controller;

import fr.esgi.cocotton.application.user.AddUser;
import fr.esgi.cocotton.domain.models.user.User;
import fr.esgi.cocotton.infrastructure.common.security.TokenProvider;
import fr.esgi.cocotton.infrastructure.authentication.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManager;
    private final AddUser addUser;

    @Autowired
    public AuthenticationController(TokenProvider tokenProvider,
                                    AuthenticationManagerBuilder authenticationManager,
                                    AddUser addUser) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.addUser = addUser;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        Authentication authentication = authenticationManager.getObject().authenticate(authenticationToken);

        String token = tokenProvider.createToken(authentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        return new ResponseEntity<>(httpHeaders,HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> save(@RequestBody User user) {
        String id = addUser.execute(user);
        URI uri = ServletUriComponentsBuilder.fromPath("/api/users")
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
