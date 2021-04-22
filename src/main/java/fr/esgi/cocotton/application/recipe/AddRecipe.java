package fr.esgi.cocotton.application.recipe;

import fr.esgi.cocotton.domain.models.recipe.Recipe;
import fr.esgi.cocotton.domain.models.recipe.RecipeDao;
import org.springframework.stereotype.Service;

@Service
public class AddRecipe {

    private final RecipeDao recipeDao;

    public AddRecipe(RecipeDao recipeDao){
        this.recipeDao = recipeDao;
    }

    public String execute(Recipe recipe){
        return recipeDao.save(recipe);
    }
}
