package fr.esgi.cocotton.profile.application;

import fr.esgi.cocotton.common.security.TokenProvider;
import fr.esgi.cocotton.profile.domain.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpHeaders;

@Service
public class FindProfileFromToken {

    private final FindProfileById findProfileById;
    private final TokenProvider tokenProvider;

    @Autowired
    public FindProfileFromToken(FindProfileById findProfileById, TokenProvider tokenProvider) {
        this.findProfileById = findProfileById;
        this.tokenProvider = tokenProvider;
    }

    public Profile execute(String token) {
        String userId = tokenProvider.parseToken(token.substring(7)).getBody().getSubject();
        return findProfileById.execute(userId);
    }
}
