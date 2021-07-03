package fr.esgi.cocotton.comment.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaCommentRepository extends JpaRepository<JpaComment, String> {

    List<JpaComment> findAllByUserId(String userId);
}
