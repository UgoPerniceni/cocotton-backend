package fr.esgi.cocotton.file.infrastructure.controller;

import com.jayway.restassured.http.ContentType;
import fr.esgi.cocotton.AbstractBigTest;
import fr.esgi.cocotton.recipe.domain.Recipe;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.HttpStatus.*;

public class RecipeControllerBigTest extends AbstractBigTest {

    private String token;
    private String testUserId;
    private String currentRecipeId;
    private Recipe recipe = Recipe.builder()
            .title("a recipe title")
            .people(4)
            .userId(null)
            .steps(Arrays.asList("step one", "step two", "step three"))
            .build();

    @Before
    public void init() {
        this.testUserId = registerTestUser();
        this.token = tokenProvider();
        recipe.setUserId(this.testUserId);

        this.currentRecipeId = given()
                .headers(
                        "Authorization",
                        this.token,
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON
                )
                .contentType(JSON)
                .body(toJson(recipe))
                .when()
                .post("/api/recipes")
                .getHeader("Location")
                .split("recipes/")[1];
    }

    @Test
    public void should_create_1_recipe() {
        String recipeId = given()
                .headers(
                        "Authorization",
                        this.token,
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON
                )
                .contentType(JSON)
                .body(toJson(this.recipe))
                .when()
                .post("/api/recipes")
                .then()
                .log().all()
                .statusCode(CREATED.value())
                .extract().header("Location")
                .split("recipes/")[1];

        given()
                .headers(
                        "Authorization",
                        this.token
                )
                .when()
                .delete("/api/recipes/" + recipeId)
                .then()
                .log().all()
                .statusCode(NO_CONTENT.value());
    }

    @Test
    public void should_get_list_of_1_file() {
        given()
                .headers(
                        "Authorization",
                        this.token
                )
                .when()
                .get("/api/recipes/")
                .then()
                .log().all()
                .statusCode(OK.value())
                .body("$", hasSize(1));
    }

    @Test
    public void should_update_file() {
        Recipe updateRecipe = Recipe.builder()
                .id(this.currentRecipeId)
                .title("a recipe title update")
                .people(4)
                .userId(null)
                .steps(Arrays.asList("step one", "step two", "step three"))
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
                .body(toJson(updateRecipe))
                .when()
                .post("/api/recipes")
                .then()
                .log().all()
                .statusCode(CREATED.value());

        Recipe fetchedRecipe = given()
                .headers(
                        "Authorization",
                        this.token
                )
                .when()
                .get("/api/recipes/" + this.currentRecipeId)
                .then()
                .extract()
                .as(Recipe.class);
        Assert.assertEquals(updateRecipe, fetchedRecipe);
    }

    @After
    public void clear() {
        given()
                .headers(
                        "Authorization",
                        this.token
                )
                .when()
                .delete("/api/recipes/" + this.currentRecipeId)
                .then()
                .log().all()
                .statusCode(NO_CONTENT.value());

        deleteTestUser(this.testUserId, this.token);
    }
}
