package fr.esgi.cocotton.comment.infrastructure.controller;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import fr.esgi.cocotton.AbstractBigTest;
import fr.esgi.cocotton.comment.domain.Comment;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.HttpStatus.*;

public class CommentControllerBigTest extends AbstractBigTest {

    private String token;
    private String testUserId;
    private String currentCommentId;
    private final String content = "a comment content with bra";
    private final String censoredContent = "a comment content with ***";

    private Comment comment = Comment.builder().build();

    @Before
    public void init() {
        this.comment.setContent(this.content);
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
    public void should_create_1_comment() {
        String commentId = given()
                .headers(
                        "Authorization",
                        this.token,
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON
                )
                .contentType(JSON)
                .body(toJson(this.comment))
                .when()
                .post("/api/comments")
                .then()
                .log().all()
                .statusCode(CREATED.value())
                .extract().header("Location")
                .split("comments/")[1];

        given()
                .headers(
                        "Authorization",
                        this.token
                )
                .when()
                .delete("/api/comments/" + commentId)
                .then()
                .log().all()
                .statusCode(NO_CONTENT.value());
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
        Assert.assertNotNull(fetchedComment);
    }

    @Test
    public void should_get_1_comment_by_id_with_censured_content() {
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

        Assert.assertNotEquals(this.comment.getContent(), fetchedComment.getContent());
        Assert.assertEquals(fetchedComment.getContent(), this.censoredContent);
    }

    @Test
    public void should_get_1_comment_by_user_id() {
        given()
                .headers(
                        "Authorization",
                        this.token
                )
                .when()
                .get("/api/comments/profiles/" + this.testUserId)
                .then()
                .log().all()
                .statusCode(OK.value())
                .body("$", hasSize(1));
    }

    @Test
    public void should_get_list_of_1_comment() {
        given()
                .headers(
                        "Authorization",
                        this.token
                )
                .when()
                .get("/api/comments/")
                .then()
                .log().all()
                .statusCode(OK.value())
                .body("$", hasSize(1));
    }

    @Test
    public void should_update_comment() {
        Comment updateComment = Comment.builder()
                .id(this.currentCommentId)
                .content(this.censoredContent + "update")
                .userId(this.testUserId)
                .build();

        given()
                .headers(
                        "Authorization",
                        this.token,
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON
                )
                .contentType(JSON)
                .body(toJson(updateComment))
                .when()
                .post("/api/comments")
                .then()
                .log().all()
                .statusCode(CREATED.value());

        Comment fetchedComment = given()
                .headers(
                        "Authorization",
                        this.token
                )
                .when()
                .get("/api/comments/" + this.currentCommentId)
                .then()
                .extract()
                .as(Comment.class);

        Assert.assertEquals(updateComment, fetchedComment);
    }

    @Test
    public void should_bad_request_when_get_comment_by_id_with_wrong_id() {
        String wrongFileId = "impossible";
        given()
                .headers(
                        "Authorization",
                        this.token
                )
                .when()
                .get("/api/v1/comments/" + wrongFileId)
                .then()
                .log().all()
                .statusCode(NOT_FOUND.value());
    }

    @Test
    public void should_bad_request_when_delete_comment_by_id_with_wrong_id() {
        String wrongFileId = "impossible";
        given()
                .headers(
                        "Authorization",
                        this.token
                )
                .when()
                .delete("/api/v1/comments/" + wrongFileId)
                .then()
                .log().all()
                .statusCode(NOT_FOUND.value());
    }

    @Test
    public void should_bad_request_when_update_comment_by_id_with_wrong_id() {
        String wrongFileId = "impossible";
        given()
                .headers(
                        "Authorization",
                        this.token
                )
                .when()
                .post("/api/v1/comments/" + wrongFileId)
                .then()
                .log().all()
                .statusCode(NOT_FOUND.value());
    }

    @Test
    public void should_bad_request_when_get_comment_by_user_id_with_wrong_id() {
        String wrongFileId = "impossible";
        given()
                .headers(
                        "Authorization",
                        this.token
                )
                .when()
                .post("/api/v1/comments/profiles/" + wrongFileId)
                .then()
                .log().all()
                .statusCode(NOT_FOUND.value());
    }

    @After
    public void clear() {
        Response response = given()
                .headers(
                        "Authorization",
                        this.token
                )
                .when()
                .get("/api/comments/");

        given()
                .headers(
                        "Authorization",
                        this.token
                )
                .when()
                .delete("/api/comments/" + this.currentCommentId)
                .then()
                .log().all()
                .statusCode(NO_CONTENT.value());

        deleteTestUser(this.testUserId, this.token);
    }

}
