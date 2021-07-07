package fr.esgi.cocotton.recipe.infrastructure.controller;

import com.jayway.restassured.http.ContentType;
import fr.esgi.cocotton.recipe.application.*;
import fr.esgi.cocotton.recipe.domain.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.webflow.test.MockRequestContext;

import java.util.Arrays;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.CREATED;

@RunWith(MockitoJUnitRunner.class)
public class RecipeControllerTest {
    @InjectMocks
    RecipeController recipeController;
    @Mock
    AddRecipe addRecipe;
    @Mock
    FindRecipeById findRecipeById;
    @Mock
    FindAllRecipes findAllRecipes;
    @Mock
    FindAllRecipesByUserId findAllRecipesByUserId;
    @Mock
    DeleteRecipeById deleteRecipeById;

    private final Recipe recipe = Recipe.builder()
            .title("a recipe title")
            .people(4)
            .steps(Arrays.asList("step one", "step two", "step three"))
            .build();

    private final String token = "a token";
    private final String id = "anId";


    @Before
    public void init() {
    }

    @Test
    public void should_get_list_of_recipes() {
        recipeController.findAll();
        verify(findAllRecipes).execute();
    }

    @Test
    public void should_find_1_recipe_by_id() {
        recipeController.findById(id);
        verify(findRecipeById).execute(this.id);
    }

    @Test
    public void should_get_list_of_recipes_by_user_id() {
        recipeController.findAllByUserId(this.id);
        verify(findAllRecipesByUserId).execute(this.id);
    }

    //TODO voir pour mocker avec MockHttpServletRequest
//    @Test
//    public void should_create_new_recipe() {
//        recipeController.save(recipe, this.token);
//        verify(addRecipe).execute(this.recipe, this.token);
//    }

    @Test
    public void should_delete_recipe_by_id() {
        recipeController.deleteById(this.id);
        verify(deleteRecipeById).execute(this.id);
    }
}
