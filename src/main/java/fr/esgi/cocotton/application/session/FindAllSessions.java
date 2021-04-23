package fr.esgi.cocotton.application.session;

import fr.esgi.cocotton.domain.models.session.Session;
import fr.esgi.cocotton.domain.models.session.SessionDao;
import fr.esgi.cocotton.infrastructure.session.persistence.JpaSession;
import fr.esgi.cocotton.infrastructure.session.persistence.JpaSessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllSessions {

    private final SessionDao sessionDao;

    @Autowired
    public FindAllSessions(SessionDao sessionDao){
        this.sessionDao = sessionDao;
    }

    public List<Session> execute(){
        return sessionDao.findAll();
    }
}
