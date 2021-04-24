package fr.esgi.cocotton.application.session;

import fr.esgi.cocotton.domain.models.session.Session;
import fr.esgi.cocotton.domain.models.session.SessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddSession {

    private final SessionDao sessionDao;

    @Autowired
    public AddSession(SessionDao sessionDao){
        this.sessionDao = sessionDao;
    }

    public void execute(Session session){
        sessionDao.save(session);
    }
}
