package fr.esgi.cocotton.application.session;

import fr.esgi.cocotton.domain.models.session.Session;
import fr.esgi.cocotton.domain.models.session.SessionDao;
import fr.esgi.cocotton.infrastructure.common.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindSessionByToken {

    private final SessionDao sessionDao;

    @Autowired
    public FindSessionByToken(SessionDao sessionDao){
        this.sessionDao = sessionDao;
    }

    public Session execute(String token){
        return sessionDao.findByToken(token).orElseThrow(() -> new ResourceNotFoundException("session", token));
    }
}
