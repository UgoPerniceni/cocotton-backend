package fr.esgi.cocotton.session.application;

import fr.esgi.cocotton.common.exception.ResourceNotFoundException;
import fr.esgi.cocotton.session.domain.Session;
import fr.esgi.cocotton.session.domain.SessionDao;
import org.springframework.stereotype.Service;

@Service
public class FindSessionById {

    private final SessionDao sessionDao;

    public FindSessionById(SessionDao sessionDao){
        this.sessionDao = sessionDao;
    }

    public Session execute(String id) {
        return sessionDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("session", "id", id));
    }
}
