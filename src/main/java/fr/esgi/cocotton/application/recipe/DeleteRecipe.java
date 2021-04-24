package fr.esgi.cocotton.application.recipe;

import fr.esgi.cocotton.domain.models.recipe.RecipeDao;
import org.springframework.stereotype.Service;

@Service
public class DeleteRecipe {

    private final RecipeDao recipeDao;
    private final FindRecipeById findRecipeById;

    public DeleteRecipe(RecipeDao recipeDao, FindRecipeById findRecipeById) {
        this.recipeDao = recipeDao;
        this.findRecipeById = findRecipeById;
    }

    public void execute(String id){
        findRecipeById.execute(id);
        recipeDao.deleteById(id);
    }
}
