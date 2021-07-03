package fr.esgi.cocotton.comment.application;

import fr.esgi.cocotton.comment.domain.Comment;
import fr.esgi.cocotton.comment.domain.CommentDao;
import org.springframework.stereotype.Service;

@Service
public class AddComment {

    private final CommentDao commentDao;

    public AddComment(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public String execute(Comment comment) {
        return commentDao.save(comment);
    }
}
