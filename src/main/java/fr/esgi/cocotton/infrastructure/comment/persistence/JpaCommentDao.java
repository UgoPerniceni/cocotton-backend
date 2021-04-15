package fr.esgi.cocotton.infrastructure.comment.persistence;

import fr.esgi.cocotton.domain.models.comment.Comment;
import fr.esgi.cocotton.domain.models.comment.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaCommentDao implements CommentDao {
    private final JpaCommentRepository repository;

    @Autowired
    public JpaCommentDao(JpaCommentRepository repository){
        this.repository = repository;
    }

    public List<Comment> findAll() {
        return repository.findAll()
                .stream()
                .map(comment -> new Comment (
                        comment.getId(),
                        comment.getTitle(),
                        comment.getContent()
                ))
                .collect(Collectors.toList());
    }

    public Optional<Comment> findById(String id) {
        Optional<JpaComment> jpaComment = repository.findById(id);
        return jpaComment.map(comment -> new Comment(
                comment.getId(),
                comment.getTitle(),
                comment.getContent()
        ));
    }

    public String save(Comment comment){
        JpaComment jpaComment = new JpaComment(
                comment.getId(),
                comment.getTitle(),
                comment.getContent()
        );
        repository.save(jpaComment);

        return jpaComment.getId();
    }

    @Transactional
    public void deleteById(String id){
        repository.deleteById(id);
    }
}
