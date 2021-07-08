package fr.esgi.cocotton.recipe.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import fr.esgi.cocotton.recipe.domain.Recipe;
import fr.esgi.cocotton.recipe.domain.RecipeDao;
import org.springframework.stereotype.Service;

@Service
public class UpdateRecipe {

    private final RecipeDao recipeDao;
    private final FindRecipeById findRecipeById;

    public UpdateRecipe(RecipeDao recipeDao, FindRecipeById findRecipeById) {
        this.recipeDao = recipeDao;
        this.findRecipeById = findRecipeById;
    }

    public String execute(JsonPatch patch, String id) {
        Recipe patchRecipe = findRecipeById.execute(id);
        try {
            patchRecipe =  recipeDao.applyPatch(patch, patchRecipe);
        } catch (JsonPatchException | JsonProcessingException e) {
            return id;
        }
        return recipeDao.save(patchRecipe);
    }
}
