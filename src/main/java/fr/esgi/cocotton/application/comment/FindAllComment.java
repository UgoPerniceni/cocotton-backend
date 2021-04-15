package fr.esgi.cocotton.application.comment;

import fr.esgi.cocotton.domain.models.comment.Comment;
import fr.esgi.cocotton.domain.models.comment.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllComment {
    private final CommentDao commentDao;

    @Autowired
    public FindAllComment(CommentDao commentDao){
        this.commentDao = commentDao;
    }

    public List<Comment> execute(){
        return commentDao.findAll();
    }
}
