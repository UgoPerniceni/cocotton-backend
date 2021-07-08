package fr.esgi.cocotton.comment.application;

import fr.esgi.cocotton.authentication.application.Login;
import fr.esgi.cocotton.authentication.application.Register;
import fr.esgi.cocotton.authentication.application.dto.LoginDTO;
import fr.esgi.cocotton.authentication.application.dto.RegisterDTO;
import fr.esgi.cocotton.comment.domain.Comment;
import fr.esgi.cocotton.common.exception.ResourceNotFoundException;
import fr.esgi.cocotton.profile.application.DeleteProfileById;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentApplicationTest {

    @Autowired
    Login login;
    @Autowired
    Register register;
    @Autowired
    DeleteProfileById deleteProfileById;
    @Autowired
    AddComment addComment;
    @Autowired
    DeleteCommentById deleteCommentById;
    @Autowired
    FindAllComments findAllComments;
    @Autowired
    FindAllCommentsByUserId findAllCommentsByUserId;
    @Autowired
    FindCommentById findCommentById;

    private String currentCommentId;
    private String userId;
    private String token;

    RegisterDTO registerDTO = RegisterDTO.builder()
            .lastName("tester_lastName")
            .firstName("tester_firstName")
            .username("tester_username_comment")
            .email("tester_comment@gmail.com")
            .password("Azerty1_$")
            .birthDate(LocalDate.of(2020, 1, 8))
            .build();

    LoginDTO loginDTO = LoginDTO.builder()
            .username("tester_username_comment")
            .password("Azerty1_$")
            .build();

    Comment comment = Comment.builder()
            .userId(userId)
            .build();

    private final String content = "a comment content with bra";
    private final String censoredContent = "a comment content with ***";

    @Before
    public void init() {
        this.comment.setContent(this.content);
        this.userId = register.execute(this.registerDTO).toString().replace("/profiles/", "");
        this.token = login.execute(this.loginDTO).get("Authorization").get(0);
        this.currentCommentId = addComment.execute(this.comment, this.token);
    }

    @Test
    public void should_find_comment_by_id() {
        Comment comment = findCommentById.execute(this.currentCommentId);

        assertThat(comment).isNotNull();
        assertThat(comment.getId()).isEqualTo(this.currentCommentId);
    }

    @Test
    public void should_save_comment() {
        String id = addComment.execute(this.comment, this.token);
        assertThat(id).isNotNull();
        deleteCommentById.execute(id);
    }

    @Test
    public void should_save_comment_with_censored_content() {
        String id = addComment.execute(this.comment, this.token);
        Comment fetchedComment = findCommentById.execute(id);
        assertThat(fetchedComment.getContent()).isEqualTo(this.censoredContent);
        deleteCommentById.execute(id);
    }

    @Test
    public void should_find_all_comments() {
        List<Comment> commentList = findAllComments.execute();
        assertThat(commentList).hasSize(1);
    }

    @Test
    public void should_find_all_comments_by_user() {
        List<Comment> commentList = findAllCommentsByUserId.execute(this.userId);
        assertThat(commentList).hasSize(1);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void should_delete_comment_by_id() {
        String id = addComment.execute(this.comment, this.token);
        deleteCommentById.execute(id);
        assertThat(findCommentById.execute(id)).isNull();
    }

    @After
    public void clear() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", this.token);
        deleteCommentById.execute(this.currentCommentId);
        deleteProfileById.execute(this.userId, headers);
    }
}
