package fr.esgi.cocotton.session.domain;

import java.util.List;
import java.util.Optional;

public interface SessionDao {

    List<Session> findAll();

    Optional<Session> findById(String id);

    List<Session> findAllByUserId(String userId);

    Optional<Session> findLastByUserId(String userId);

    void save(Session session);
}
