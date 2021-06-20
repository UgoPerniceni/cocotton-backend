package fr.esgi.cocotton.session.application;

import fr.esgi.cocotton.session.domain.Session;
import fr.esgi.cocotton.session.domain.SessionDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllSessionsByUserId {

    private final SessionDao sessionDao;

    public FindAllSessionsByUserId(SessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    public List<Session> execute(String userId) {
        return sessionDao.findAllByUserId(userId);
    }
}
