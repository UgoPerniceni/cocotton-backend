package fr.esgi.cocotton.domain.models.comment;

import java.util.List;
import java.util.Optional;

public interface CommentDao {
    List<Comment> findAll();

    Optional<Comment> findById(String id);

    String save(Comment comment);

    void deleteById(String id);
}
