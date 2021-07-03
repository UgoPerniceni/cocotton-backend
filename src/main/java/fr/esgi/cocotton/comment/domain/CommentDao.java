package fr.esgi.cocotton.comment.domain;

import java.util.List;
import java.util.Optional;

public interface CommentDao {

    List<Comment> findAll();

    Optional<Comment> findById(String id);

    List<Comment> findAllByUserId(String userId);

    String save(Comment comment);

    void deleteById(String id);

//    String censorCommentContent(Comment comment);
}
