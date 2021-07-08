package fr.esgi.cocotton.comment.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import fr.esgi.cocotton.comment.domain.Comment;
import fr.esgi.cocotton.comment.domain.CommentDao;
import org.springframework.stereotype.Service;

import static fr.esgi.cocotton.comment.domain.CensorCommentContent.censorComment;

@Service
public class UpdateComment {

    private final CommentDao commentDao;
    private final FindCommentById findCommentById;

    public UpdateComment(CommentDao commentDao, FindCommentById findCommentById) {
        this.commentDao = commentDao;
        this.findCommentById = findCommentById;
    }

    public String execute(JsonPatch patch, String id) {
        Comment patchComment = findCommentById.execute(id);
        try {
            patchComment =  commentDao.applyPatch(patch, patchComment);
            patchComment.setContent(censorComment(patchComment.getContent()));
        } catch (JsonPatchException | JsonProcessingException e) {
            return id;
        }
        return commentDao.save(patchComment);
    }
}
