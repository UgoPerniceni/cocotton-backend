package fr.esgi.cocotton.application.authentication;

import fr.esgi.cocotton.application.authentication.dto.LoginDTO;
import fr.esgi.cocotton.application.profile.FindProfileByUsername;
import fr.esgi.cocotton.application.session.AddSession;
import fr.esgi.cocotton.application.profile.FindProfileByEmail;
import fr.esgi.cocotton.domain.models.session.Session;
import fr.esgi.cocotton.domain.models.profile.Profile;
import fr.esgi.cocotton.infrastructure.common.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
public class Login {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AddSession addSession;
    private final FindProfileByUsername findProfileByUsername;

    @Autowired
    public Login(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, AddSession addSession, FindProfileByUsername findProfileByUsername){
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.addSession = addSession;
        this.findProfileByUsername = findProfileByUsername;
    }

    public HttpHeaders execute(LoginDTO loginDTO){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(),
                loginDTO.getPassword());

        Profile user = findProfileByUsername.execute(loginDTO.getUsername());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        String token = tokenProvider.createToken(authentication);

        addSession.execute(new Session(user.getId(), null, token, user));


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        return httpHeaders;
    }
}
