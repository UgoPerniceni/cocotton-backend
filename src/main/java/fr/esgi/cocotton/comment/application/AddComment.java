package fr.esgi.cocotton.comment.application;

import fr.esgi.cocotton.comment.domain.Comment;
import fr.esgi.cocotton.comment.domain.CommentDao;
import fr.esgi.cocotton.profile.application.FindProfileFromToken;
import fr.esgi.cocotton.profile.domain.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddComment {

    private final CommentDao commentDao;
    private final FindProfileFromToken findProfileFromToken;

    @Autowired
    public AddComment(CommentDao commentDao, FindProfileFromToken findProfileFromToken) {
        this.commentDao = commentDao;
        this.findProfileFromToken = findProfileFromToken;
    }

    public String execute(Comment comment, String token) {
        Profile profile = findProfileFromToken.execute(token);
        comment.setUserId(profile.getId());
        return commentDao.save(comment);
    }
}
