package fr.esgi.cocotton.domain.models.session;

import fr.esgi.cocotton.domain.models.user.User;

import java.util.List;
import java.util.Optional;

public interface SessionDao {

    List<Session> findAll();

    List<Session> findAllByUser(User user);

    Optional<Session> findByToken(String token);

    void save(Session session);
}
