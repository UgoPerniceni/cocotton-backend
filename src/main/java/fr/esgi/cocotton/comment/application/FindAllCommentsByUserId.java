package fr.esgi.cocotton.comment.application;

import fr.esgi.cocotton.comment.domain.Comment;
import fr.esgi.cocotton.comment.domain.CommentDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllCommentsByUserId {

    private final CommentDao commentDao;

    public FindAllCommentsByUserId(CommentDao commentDao){
        this.commentDao = commentDao;
    }

    public List<Comment> execute(String userId) {
        return commentDao.findAllByUserId(userId);
    }
}
