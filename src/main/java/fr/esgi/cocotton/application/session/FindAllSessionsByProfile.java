package fr.esgi.cocotton.application.session;

import fr.esgi.cocotton.domain.models.session.Session;
import fr.esgi.cocotton.domain.models.session.SessionDao;
import fr.esgi.cocotton.domain.models.profile.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllSessionsByProfile {

    private final SessionDao sessionDao;

    @Autowired
    public FindAllSessionsByProfile(SessionDao sessionDao){
        this.sessionDao = sessionDao;
    }

    public List<Session> execute(Profile user){
        return sessionDao.findAllByUser(user);
    }
}
