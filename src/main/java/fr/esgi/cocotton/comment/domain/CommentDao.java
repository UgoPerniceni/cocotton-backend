package fr.esgi.cocotton.comment.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import java.util.List;
import java.util.Optional;

public interface CommentDao {

    List<Comment> findAll();

    Optional<Comment> findById(String id);

    List<Comment> findAllByUserId(String userId);

    String save(Comment comment);

    void deleteById(String id);
    
    Comment applyPatch(JsonPatch patch, Comment targetComment) throws JsonPatchException, JsonProcessingException;

}
