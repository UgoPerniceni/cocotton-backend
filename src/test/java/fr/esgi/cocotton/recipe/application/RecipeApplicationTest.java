package fr.esgi.cocotton.recipe.application;

import fr.esgi.cocotton.authentication.application.Login;
import fr.esgi.cocotton.authentication.application.Register;
import fr.esgi.cocotton.authentication.application.dto.LoginDTO;
import fr.esgi.cocotton.authentication.application.dto.RegisterDTO;
import fr.esgi.cocotton.common.exception.ResourceNotFoundException;
import fr.esgi.cocotton.profile.application.DeleteProfileById;
import fr.esgi.cocotton.recipe.domain.Recipe;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeApplicationTest {

        @Autowired
        Login login;
        @Autowired
        Register register;
        @Autowired
        DeleteProfileById deleteProfileById;
        @Autowired
        AddRecipe addRecipe;
        @Autowired
        DeleteRecipeById deleteRecipeById;
        @Autowired
        FindAllRecipes findAllRecipes;
        @Autowired
        FindAllRecipesByUserId findAllRecipesByUserId;
        @Autowired
        FindRecipeById findRecipeById;

        private String currentRecipeId;
        private String userId;
        private String token;

        RegisterDTO registerDTO = RegisterDTO.builder()
                .lastName("tester_lastName")
                .firstName("tester_firstName")
                .username("tester_username_recipe")
                .email("tester_recipe@gmail.com")
                .password("Azerty1_$")
                .birthDate(LocalDate.of(2020, 1, 8))
                .build();

        LoginDTO loginDTO = LoginDTO.builder()
                .username("tester_username_recipe")
                .password("Azerty1_$")
                .build();

        Recipe recipe = Recipe.builder()
                .title("a recipe title")
                .people(4)
                .steps(Arrays.asList("step one", "step two", "step three"))
                .build();

        @Before
        public void init() {
            this.userId = register.execute(this.registerDTO).toString().replace("/profiles/", "");
            this.token = login.execute(this.loginDTO).get("Authorization").get(0);
            this.currentRecipeId = addRecipe.execute(this.recipe, this.token);
        }

        @Test
        public void should_find_recipe_by_id() {
            Recipe recipe = findRecipeById.execute(this.currentRecipeId);

            assertThat(recipe).isNotNull();
            assertThat(recipe.getId()).isEqualTo(this.currentRecipeId);
        }

        @Test
        public void should_save_recipe() {
            String id = addRecipe.execute(this.recipe, this.token);
            assertThat(id).isNotNull();
            deleteRecipeById.execute(id);
        }

        @Test
        public void should_find_all_recipes() {
            List<Recipe> recipeList = findAllRecipes.execute();
            assertThat(recipeList).hasSize(1);
        }

        @Test
        public void should_find_all_recipes_by_user() {
            List<Recipe> recipeList = findAllRecipesByUserId.execute(this.userId);
            assertThat(recipeList).hasSize(1);
        }

        @Test(expected = ResourceNotFoundException.class)
        public void should_delete_recipe_by_id() {
            String id = addRecipe.execute(this.recipe, this.token);
            deleteRecipeById.execute(id);
            assertThat(findRecipeById.execute(id)).isNull();
        }

        @After
        public void clear() {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", this.token);
            deleteRecipeById.execute(this.currentRecipeId);
            deleteProfileById.execute(this.userId, headers);
        }
}