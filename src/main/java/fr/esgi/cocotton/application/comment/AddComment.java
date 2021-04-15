package fr.esgi.cocotton.application.comment;

import fr.esgi.cocotton.domain.models.comment.Comment;
import fr.esgi.cocotton.domain.models.comment.CommentDao;
import org.springframework.stereotype.Service;

@Service
public class AddComment {
    private final CommentDao commentDao;

    public AddComment(CommentDao commentDao){
        this.commentDao = commentDao;
    }

    public String execute(Comment comment) {
        return commentDao.save(comment);
    }
}
