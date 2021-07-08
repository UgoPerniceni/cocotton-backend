package fr.esgi.cocotton.recipe.infrastructure.controller;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import fr.esgi.cocotton.AbstractBigTest;
import fr.esgi.cocotton.recipe.domain.Recipe;
import org.junit.*;

import java.util.Arrays;
import java.util.Set;

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
            .steps(Arrays.asList("step one", "step two", "step three"))
            .ingredients(Set.of("926105ee-a40f-4e92-b6aa-738824027889"))
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
    public void should_get_1_recipe_by_id() {
        Recipe fetchedRecipe = given()
                .headers(
                        "Authorization",
                        this.token
                )
                .when()
                .get("/api/recipes/" + this.currentRecipeId)
                .then()
                .log().all()
                .statusCode(OK.value())
                .extract()
                .as(Recipe.class);

        this.recipe.setId(this.currentRecipeId);

        Assert.assertEquals(this.recipe, fetchedRecipe);
    }

    @Test
    public void should_get_1_recipe_by_user_id() {
        given()
                .headers(
                        "Authorization",
                        this.token
                )
                .when()
                .get("/api/recipes/profiles/" + this.testUserId)
                .then()
                .log().all()
                .statusCode(OK.value())
                .body("$", hasSize(1));
    }

    @Test
    public void should_get_list_of_1_recipe() {
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
    public void should_update_recipe() {
        Recipe updateRecipe = Recipe.builder()
                .id(this.currentRecipeId)
                .title("a recipe title update")
                .people(4)
                .userId(this.testUserId)
                .steps(Arrays.asList("step one", "step two", "step three"))
                .ingredients(Set.of("926105ee-a40f-4e92-b6aa-738824027889"))
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

    @Test
    public void should_bad_request_when_get_recipe_by_id_with_wrong_id() {
        String wrongFileId = "impossible";
        given()
                .headers(
                        "Authorization",
                        this.token
                )
                .when()
                .get("/api/recipes/" + wrongFileId)
                .then()
                .log().all()
                .statusCode(NOT_FOUND.value());
    }

    @Test
    public void should_bad_request_when_delete_recipe_by_id_with_wrong_id() {
        String wrongFileId = "impossible";
        given()
                .headers(
                        "Authorization",
                        this.token
                )
                .when()
                .delete("/api/recipes/" + wrongFileId)
                .then()
                .log().all()
                .statusCode(NOT_FOUND.value());
    }

    @Ignore
    @Test
    public void should_bad_request_when_update_recipe_by_id_with_wrong_id() {
        String wrongFileId = "impossible";
        given()
                .headers(
                        "Authorization",
                        this.token
                )
                .when()
                .post("/api/recipes/" + wrongFileId)
                .then()
                .log().all()
                .statusCode(NOT_FOUND.value());
    }

    @Ignore
    @Test
    public void should_bad_request_when_get_recipe_by_user_id_with_wrong_id() {
        String wrongFileId = "impossible";
        given()
                .headers(
                        "Authorization",
                        this.token
                )
                .when()
                .post("/api/recipes/profiles/" + wrongFileId)
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
                .get("/api/recipes/");

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
