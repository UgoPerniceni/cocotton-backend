package fr.esgi.cocotton.recipe.application;

import fr.esgi.cocotton.recipe.domain.Recipe;
import fr.esgi.cocotton.recipe.domain.RecipeDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllRecipesByUserId {

    private final RecipeDao recipeDao;

    public FindAllRecipesByUserId(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }

    public List<Recipe> execute(String userId) {
        return recipeDao.findAllByUserId(userId);
    }
}
