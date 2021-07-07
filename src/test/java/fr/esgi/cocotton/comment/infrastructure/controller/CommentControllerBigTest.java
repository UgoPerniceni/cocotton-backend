package fr.esgi.cocotton.comment.infrastructure.controller;

import com.jayway.restassured.http.ContentType;
import fr.esgi.cocotton.AbstractBigTest;
import fr.esgi.cocotton.comment.domain.Comment;
import fr.esgi.cocotton.recipe.domain.Recipe;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.springframework.http.HttpStatus.OK;

public class CommentControllerBigTest extends AbstractBigTest {

    private String token;
    private String testUserId;
    private String currentCommentId;
    private Comment comment = Comment.builder()
            .content("a comment content")
            .build();

    @Before
    public void init() {
        this.testUserId = registerTestUser();
        this.token = tokenProvider();
        comment.setUserId(this.testUserId);

        this.currentCommentId = given()
                .headers(
                        "Authorization",
                        this.token,
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON
                )
                .contentType(JSON)
                .body(toJson(comment))
                .when()
                .post("/api/comments")
                .getHeader("Location")
                .split("comments/")[1];
    }

    @Test
    public void should_get_1_comment_by_id() {
        Comment fetchedComment = given()
                .headers(
                        "Authorization",
                        this.token
                )
                .when()
                .get("/api/comments/" + this.currentCommentId)
                .then()
                .log().all()
                .statusCode(OK.value())
                .extract()
                .as(Comment.class);

        this.comment.setId(this.currentCommentId);

        Assert.assertEquals(this.comment, fetchedComment);
    }

}
