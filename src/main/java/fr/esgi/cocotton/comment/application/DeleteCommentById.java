package fr.esgi.cocotton.comment.application;

import fr.esgi.cocotton.comment.domain.CommentDao;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommentById {

    private final CommentDao commentDao;

    public DeleteCommentById(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public void execute(String id) {
        commentDao.deleteById(id);
    }
}
