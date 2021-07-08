package fr.esgi.cocotton.comment.infrastructure.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import fr.esgi.cocotton.comment.domain.Comment;
import fr.esgi.cocotton.comment.domain.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    @Override
    public List<Comment> findAll() {
        return repository.findAll()
                .stream()
                .map(jpaComment -> new Comment(jpaComment.getId(), jpaComment.getContent(), jpaComment.getUserId()))
                .collect(Collectors.toList());
    }

    public List<Comment> findAllByUserId(String userId) {
        return repository.findAllByUserId(userId)
                .stream()
                .map(jpaComment -> new Comment(jpaComment.getId(), jpaComment.getContent(), jpaComment.getUserId()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Comment> findById(String id) {
        Optional<JpaComment> jpaComment = repository.findById(id);
        return jpaComment.map(comment -> new Comment(comment.getId(), comment.getContent(), comment.getUserId()));
    }

    @Override
    public String save(Comment comment) {
        JpaComment jpaComment = new JpaComment(comment.getId(), comment.getContent(), comment.getUserId());
        repository.save(jpaComment);
        return jpaComment.getId();
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public Comment applyPatch(JsonPatch patch, Comment targetComment) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(targetComment, JsonNode.class));
        return objectMapper.treeToValue(patched, Comment.class);
    }
}
