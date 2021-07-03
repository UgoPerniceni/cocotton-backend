package fr.esgi.cocotton.comment.application;

import fr.esgi.cocotton.comment.domain.Comment;
import fr.esgi.cocotton.comment.domain.CommentDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllComments {

    private final CommentDao commentDao;

    public FindAllComments(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public List<Comment> execute() {
        return commentDao.findAll();
    }
}
