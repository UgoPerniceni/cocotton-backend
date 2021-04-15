package fr.esgi.cocotton.infrastructure.comment.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaCommentRepository extends JpaRepository<JpaComment, Integer> {
    Optional<JpaComment> findById(String id);
    void deleteById(String id);
}
