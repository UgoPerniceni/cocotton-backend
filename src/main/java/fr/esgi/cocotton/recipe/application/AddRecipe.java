package fr.esgi.cocotton.recipe.application;

import fr.esgi.cocotton.recipe.domain.Recipe;
import fr.esgi.cocotton.recipe.domain.RecipeDao;
import org.springframework.stereotype.Service;

@Service
public class AddRecipe {

    private final RecipeDao recipeDao;

    public AddRecipe(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }

    public String execute(Recipe recipe) {
        return recipeDao.save(recipe);
    }
}
