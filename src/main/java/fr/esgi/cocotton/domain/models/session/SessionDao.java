package fr.esgi.cocotton.domain.models.session;

import fr.esgi.cocotton.domain.models.profile.Profile;

import java.util.List;
import java.util.Optional;

public interface SessionDao {

    List<Session> findAll();

    List<Session> findAllByUser(Profile user);

    Optional<Session> findByToken(String token);

    void save(Session session);
}
