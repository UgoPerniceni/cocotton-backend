package fr.esgi.cocotton.comment.application;

import fr.esgi.cocotton.comment.domain.Comment;
import fr.esgi.cocotton.comment.domain.CommentDao;
import fr.esgi.cocotton.common.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FindCommentById {

    private final CommentDao commentDao;

    public FindCommentById(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public Comment execute(String id) {
        return commentDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("comment", "id", id));
    }
}
