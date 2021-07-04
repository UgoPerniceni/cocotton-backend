package fr.esgi.cocotton.comment.application;

import fr.esgi.cocotton.comment.domain.Comment;
import fr.esgi.cocotton.comment.domain.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static fr.esgi.cocotton.comment.domain.CensorCommentContent.censorComment;

@Service
public class AddComment {

    private final CommentDao commentDao;

    @Autowired
    public AddComment(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public String execute(Comment comment) {
        comment.setContent(censorComment(comment.getContent()));
        return commentDao.save(comment);
    }
}
