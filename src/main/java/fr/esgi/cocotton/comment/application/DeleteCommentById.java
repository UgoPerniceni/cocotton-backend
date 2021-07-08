package fr.esgi.cocotton.comment.application;

import fr.esgi.cocotton.comment.domain.CommentDao;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommentById {

    private final CommentDao commentDao;
    private final FindCommentById findCommentById;

    public DeleteCommentById(CommentDao commentDao, FindCommentById findCommentById) {
        this.commentDao = commentDao;
        this.findCommentById = findCommentById;
    }

    public void execute(String id) {
        findCommentById.execute(id);
        commentDao.deleteById(id);
    }
}
