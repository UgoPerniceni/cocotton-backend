package fr.esgi.cocotton.application.recipe;

import fr.esgi.cocotton.application.profile.FindProfileFromHeader;
import fr.esgi.cocotton.domain.models.recipe.Recipe;
import fr.esgi.cocotton.domain.models.recipe.RecipeDao;
import org.springframework.stereotype.Service;

@Service
public class AddRecipe {

    private final RecipeDao recipeDao;
    private final FindProfileFromHeader findProfileFromHeader;

    public AddRecipe(RecipeDao recipeDao, FindProfileFromHeader findProfileFromHeader){
        this.recipeDao = recipeDao;
        this.findProfileFromHeader = findProfileFromHeader;
    }

    public String execute(Recipe recipe, String token){
        recipe.setProfile(findProfileFromHeader.execute(token));
        return recipeDao.save(recipe);
    }
}
