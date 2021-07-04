package fr.esgi.cocotton.recipe.application;

import fr.esgi.cocotton.profile.application.FindProfileFromToken;
import fr.esgi.cocotton.profile.domain.Profile;
import fr.esgi.cocotton.recipe.domain.Recipe;
import fr.esgi.cocotton.recipe.domain.RecipeDao;
import org.springframework.stereotype.Service;

@Service
public class AddRecipe {

    private final RecipeDao recipeDao;
    private final FindProfileFromToken findProfileFromToken;

    public AddRecipe(RecipeDao recipeDao, FindProfileFromToken findProfileFromToken) {
        this.recipeDao = recipeDao;
        this.findProfileFromToken = findProfileFromToken;
    }

    public String execute(Recipe recipe, String token) {
        Profile profile = findProfileFromToken.execute(token);
        recipe.setUserId(profile.getId());
        return recipeDao.save(recipe);
    }
}
