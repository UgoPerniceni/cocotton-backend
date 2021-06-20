package fr.esgi.cocotton.profile.application;

import fr.esgi.cocotton.common.exception.ResourceOwnershipException;
import fr.esgi.cocotton.common.security.TokenProvider;
import fr.esgi.cocotton.profile.domain.ProfileDao;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DeleteProfileById {

    private final ProfileDao profileDao;
    private final FindProfileById findProfileById;
    private final TokenProvider tokenProvider;

    public DeleteProfileById(ProfileDao profileDao, FindProfileById findProfileById, TokenProvider tokenProvider){
        this.profileDao = profileDao;
        this.findProfileById = findProfileById;
        this.tokenProvider = tokenProvider;
    }

    public void execute(String id, HttpHeaders headers) {
        String token = Objects.requireNonNull(headers.getFirst("Authorization")).substring(7);
        String userId = tokenProvider.parseToken(token).getBody().getSubject();
        if(!id.equals(userId)) {
            throw new ResourceOwnershipException("profile");
        }
        findProfileById.execute(id);
        profileDao.deleteById(id);
    }
}
