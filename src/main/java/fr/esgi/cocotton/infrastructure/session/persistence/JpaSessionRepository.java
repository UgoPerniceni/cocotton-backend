package fr.esgi.cocotton.infrastructure.session.persistence;

import fr.esgi.cocotton.infrastructure.user.persistance.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaSessionRepository extends JpaRepository<JpaSession, String> {

    List<JpaSession> findAll();

    List<JpaSession> findAllByJpaUser(JpaUser jpaUser);

    Optional<JpaSession> findByToken(String token);

    Optional<JpaSession> findById(String id);
}
