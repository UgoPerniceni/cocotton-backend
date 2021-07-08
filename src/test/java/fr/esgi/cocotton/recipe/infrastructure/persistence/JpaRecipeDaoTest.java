package fr.esgi.cocotton.recipe.infrastructure.persistence;

import com.jayway.restassured.response.Response;
import fr.esgi.cocotton.recipe.domain.Recipe;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.jayway.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaRecipeDaoTest {

    @Autowired
    JpaRecipeDao jpaRecipeDao;

    private String currentRecipeId;
    private final String userId = "userId";

    private final Recipe recipe = Recipe.builder()
            .title("a recipe title")
            .people(4)
            .steps(Arrays.asList("step one", "step two", "step three"))
            .userId(userId)
            .build();

    @Before
    public void init() {
        this.currentRecipeId = jpaRecipeDao.save(this.recipe);
    }

    @Test
    public void should_save_recipe() {
        String id = jpaRecipeDao.save(this.recipe);
        assertThat(id).isNotNull();
        jpaRecipeDao.deleteById(id);
    }

    @Test
    public void should_find_recipe_by_id() {
        Recipe recipe = jpaRecipeDao.findById(this.currentRecipeId).get();

        assertThat(recipe).isNotNull();
        assertThat(recipe.getId()).isEqualTo(this.currentRecipeId);
    }

    @Test
    public void should_find_all_recipes() {
        List<Recipe> recipeList = jpaRecipeDao.findAll();
        System.out.println(recipeList.size());
        assertThat(recipeList).hasSize(1);
    }

    @Test
    public void should_find_all_recipes_by_user() {
        List<Recipe> recipeList = jpaRecipeDao.findAllByUserId(this.userId);
        assertThat(recipeList).hasSize(1);
    }

    @Test
    public void should_delete_recipe_by_id() {
        String toDeleteId = jpaRecipeDao.save(this.recipe);
        jpaRecipeDao.deleteById(toDeleteId);
        assertThat(jpaRecipeDao.findById(toDeleteId)).isEmpty();
    }

    @After
    public void clear() {
        jpaRecipeDao.deleteById(this.currentRecipeId);
    }
}
