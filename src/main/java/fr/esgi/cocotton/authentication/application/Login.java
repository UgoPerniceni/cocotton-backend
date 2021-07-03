package fr.esgi.cocotton.authentication.application;

import fr.esgi.cocotton.authentication.application.dto.LoginDTO;
import fr.esgi.cocotton.common.mapper.ProfileMapper;
import fr.esgi.cocotton.common.security.TokenProvider;
import fr.esgi.cocotton.profile.application.FindProfileByUsername;
import fr.esgi.cocotton.profile.domain.Profile;
import fr.esgi.cocotton.session.application.AddSession;
import fr.esgi.cocotton.session.domain.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class Login {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final FindProfileByUsername findProfileByUsername;
    private final AddSession addSession;

    @Autowired
    public Login(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, FindProfileByUsername findProfileByUsername, AddSession addSession) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.findProfileByUsername = findProfileByUsername;
        this.addSession = addSession;
    }

    public HttpHeaders execute(LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        Profile profile = findProfileByUsername.execute(loginDTO.getUsername());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        String token = tokenProvider.createToken(authentication, profile.getId());

        addSession.execute(new Session(LocalDateTime.now(), profile.getId()));

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        return headers;
    }
}
