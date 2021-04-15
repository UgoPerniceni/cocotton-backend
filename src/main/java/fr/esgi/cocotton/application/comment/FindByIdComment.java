package fr.esgi.cocotton.application.comment;

import fr.esgi.cocotton.domain.models.comment.Comment;
import fr.esgi.cocotton.domain.models.comment.CommentDao;
import fr.esgi.cocotton.infrastructure.common.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindByIdComment {
    private final CommentDao commentDao;

    @Autowired
    public FindByIdComment(CommentDao commentDao){
        this.commentDao = commentDao;
    }

    public Comment execute(String id) {
        return commentDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("comment", id));
    }
}
