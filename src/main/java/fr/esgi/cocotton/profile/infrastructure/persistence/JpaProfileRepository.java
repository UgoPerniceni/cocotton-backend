package fr.esgi.cocotton.profile.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaProfileRepository extends JpaRepository<JpaProfile, String> {
    Optional<JpaProfile> findById(String id);
    void deleteById(String id);
    Optional<JpaProfile> findByEmail(String email);
    Optional<JpaProfile> findByUsername(String username);
}
