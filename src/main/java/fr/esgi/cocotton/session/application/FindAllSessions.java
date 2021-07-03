package fr.esgi.cocotton.session.application;

import fr.esgi.cocotton.session.domain.Session;
import fr.esgi.cocotton.session.domain.SessionDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllSessions {

    private final SessionDao sessionDao;

    public FindAllSessions(SessionDao sessionDao){
        this.sessionDao = sessionDao;
    }

    public List<Session> execute(){
        return sessionDao.findAll();
    }
}
