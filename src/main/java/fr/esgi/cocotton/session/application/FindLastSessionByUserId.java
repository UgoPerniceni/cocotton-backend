package fr.esgi.cocotton.session.application;

import fr.esgi.cocotton.common.exception.ResourceNotFoundException;
import fr.esgi.cocotton.session.domain.Session;
import fr.esgi.cocotton.session.domain.SessionDao;
import org.springframework.stereotype.Service;

@Service
public class FindLastSessionByUserId {

    private final SessionDao sessionDao;

    public FindLastSessionByUserId(SessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    public Session execute(String userId) {
        return sessionDao.findLastByUserId(userId).orElseThrow(() -> new ResourceNotFoundException("session", "userId", userId));
    }
}
