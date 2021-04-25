package fr.esgi.cocotton.application.session;

import fr.esgi.cocotton.domain.models.session.Session;
import fr.esgi.cocotton.domain.models.session.SessionDao;
import fr.esgi.cocotton.domain.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllSessionsByUser {

    private final SessionDao sessionDao;

    @Autowired
    public FindAllSessionsByUser(SessionDao sessionDao){
        this.sessionDao = sessionDao;
    }

    public List<Session> execute(User user){
        return sessionDao.findAllByUser(user);
    }
}
