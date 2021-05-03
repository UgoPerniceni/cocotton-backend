package fr.esgi.cocotton.application.profile;

import fr.esgi.cocotton.application.session.FindSessionByToken;
import fr.esgi.cocotton.domain.models.profile.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindProfileFromHeader {

    private final FindSessionByToken findSessionByToken;

    @Autowired
    public FindProfileFromHeader(FindSessionByToken findSessionByToken){
        this.findSessionByToken = findSessionByToken;
    }

    public Profile execute(String token){
        token = token.substring(7);
        return findSessionByToken.execute(token).getProfile();
    }
}
