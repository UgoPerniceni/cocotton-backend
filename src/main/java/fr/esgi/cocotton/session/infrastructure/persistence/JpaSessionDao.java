package fr.esgi.cocotton.session.infrastructure.persistence;

import fr.esgi.cocotton.session.domain.Session;
import fr.esgi.cocotton.session.domain.SessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaSessionDao  implements SessionDao {

    private final JpaSessionRepository jpaSessionRepository;

    @Autowired
    public JpaSessionDao(JpaSessionRepository jpaSessionRepository) {
        this.jpaSessionRepository = jpaSessionRepository;
    }


    public List<Session> findAll() {
        return jpaSessionRepository.findAll().
                stream()
                .map(jpaSession ->
                        new Session(jpaSession.getId(),
                                jpaSession.getCreatedAt(),
                                jpaSession.getUserId()))
                .collect(Collectors.toList());
    }

    public Optional<Session> findById(String id) {
        Optional<JpaSession> jpaSession = jpaSessionRepository.findById(id);
        return jpaSession.map(session -> new Session(session.getId(), session.getCreatedAt(), session.getUserId()));
    }

    public List<Session> findAllByUserId(String userId) {
        return jpaSessionRepository.findAllByUserId(userId).
                stream()
                .map(jpaSession ->
                        new Session(jpaSession.getId(),
                                jpaSession.getCreatedAt(),
                                jpaSession.getUserId()))
                .collect(Collectors.toList());
    }

    public Optional<Session> findLastByUserId(String userId) {
        Optional<JpaSession> jpaSession = jpaSessionRepository.findFirstByUserIdOrderByCreatedAtDesc(userId);
        return jpaSession.map(session -> new Session(session.getId(), session.getCreatedAt(), session.getUserId()));
    }

    public void save(Session session) {
        JpaSession jpaSession = new JpaSession(session.getCreatedAt(), session.getUserId());
        jpaSessionRepository.save(jpaSession);
    }
}
