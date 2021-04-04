package fr.esgi.cocotton.infrastructure.user.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<JpaUser, Integer> {
    Optional<JpaUser> findById(String id);
    void deleteById(String id);
}
