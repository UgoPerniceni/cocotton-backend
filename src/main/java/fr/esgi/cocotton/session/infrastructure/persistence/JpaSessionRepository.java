package fr.esgi.cocotton.session.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaSessionRepository extends JpaRepository<JpaSession, String> {

    List<JpaSession> findAll();

    List<JpaSession> findAllByUserId(String userId);

    Optional<JpaSession> findFirstByUserIdOrderByCreatedAtDesc(String userId);

    Optional<JpaSession> findById(String id);
}
