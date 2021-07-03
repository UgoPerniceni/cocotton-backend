package fr.esgi.cocotton.recipe.application;

import fr.esgi.cocotton.common.exception.ResourceNotFoundException;
import fr.esgi.cocotton.recipe.domain.Recipe;
import fr.esgi.cocotton.recipe.domain.RecipeDao;
import org.springframework.stereotype.Service;

@Service
public class FindRecipeById {

    private final RecipeDao recipeDao;

    public FindRecipeById(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }

    public Recipe execute(String id) {
        return recipeDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("recipe", "id", id));
    }
}
