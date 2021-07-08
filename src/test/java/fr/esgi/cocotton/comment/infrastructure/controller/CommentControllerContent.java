package fr.esgi.cocotton.comment.infrastructure.controller;

import fr.esgi.cocotton.comment.application.*;
import fr.esgi.cocotton.comment.domain.Comment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CommentControllerContent {
    @InjectMocks
    CommentController commentController;
    @Mock
    AddComment addComment;
    @Mock
    FindCommentById findCommentById;
    @Mock
    FindAllComments findAllComments;
    @Mock
    FindAllCommentsByUserId findAllCommentsByUserId;
    @Mock
    DeleteCommentById deleteCommentById;

    Comment comment = Comment.builder().build();
    private final String content = "a comment content with bra";
    private final String censoredContent = "a comment content with ***";
    
    private final String token = "a token";
    private final String id = "anId";
    
    @Before
    public void init() {
        comment.setContent(this.content);
    }

    @Test
    public void should_get_list_of_comments() {
        commentController.findAll();
        verify(findAllComments).execute();
    }

    @Test
    public void should_find_1_comment_by_id() {
        commentController.findById(id);
        verify(findCommentById).execute(this.id);
    }

    @Test
    public void should_get_list_of_comments_by_user_id() {
        commentController.findAllByUser(this.id);
        verify(findAllCommentsByUserId).execute(this.id);
    }
    @Test
    public void should_delete_comment_by_id() {
        commentController.deleteById(this.id);
        verify(deleteCommentById).execute(this.id);
    }
}
