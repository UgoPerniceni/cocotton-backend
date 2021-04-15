package fr.esgi.cocotton.application.comment;

import fr.esgi.cocotton.domain.models.comment.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteComment {
    private final CommentDao CommentDao;
    private final FindByIdComment findByIdComment;

    @Autowired
    public DeleteComment(CommentDao CommentDao, FindByIdComment findByIdComment){
        this.CommentDao = CommentDao;
        this.findByIdComment = findByIdComment;
    }

    public void execute(String id){
        findByIdComment.execute(id);
        CommentDao.deleteById(id);
    }
}
