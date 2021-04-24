package fr.esgi.cocotton.application.user;

import fr.esgi.cocotton.application.session.FindSessionByToken;
import fr.esgi.cocotton.domain.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindUserFromHeader {

    private final FindSessionByToken findSessionByToken;

    @Autowired
    public FindUserFromHeader(FindSessionByToken findSessionByToken){
        this.findSessionByToken = findSessionByToken;
    }

    public User execute(String token){
        token = token.substring(7);
        return findSessionByToken.execute(token).getUser();
    }
}
