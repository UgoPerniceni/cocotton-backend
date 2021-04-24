package fr.esgi.cocotton.application.recipe;

import fr.esgi.cocotton.domain.models.recipe.Recipe;
import fr.esgi.cocotton.domain.models.recipe.RecipeDao;
import fr.esgi.cocotton.infrastructure.common.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FindRecipeById {

    private final RecipeDao recipeDao;

    public FindRecipeById(RecipeDao recipeDao){
        this.recipeDao = recipeDao;
    }
    public Recipe execute(String id) {
        return recipeDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("recipe", id));
    }
}
