package fr.esgi.cocotton.application.authentication;

import fr.esgi.cocotton.application.authentication.dto.LoginDTO;
import fr.esgi.cocotton.application.session.AddSession;
import fr.esgi.cocotton.application.user.FindUserByEmail;
import fr.esgi.cocotton.domain.models.session.Session;
import fr.esgi.cocotton.domain.models.user.User;
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
    private final FindUserByEmail findUserByEmail;

    @Autowired
    public Login(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, AddSession addSession, FindUserByEmail findUserByEmail){
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.addSession = addSession;
        this.findUserByEmail = findUserByEmail;
    }

    public HttpHeaders execute(LoginDTO loginDTO){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(),
                loginDTO.getPassword());

        User user = findUserByEmail.execute(loginDTO.getUsername());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        String token = tokenProvider.createToken(authentication);

        addSession.execute(new Session(user.getId(), null, token, user));


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        return httpHeaders;
    }
}
