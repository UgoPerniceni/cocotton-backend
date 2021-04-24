package fr.esgi.cocotton.application.recipe;

import fr.esgi.cocotton.application.user.FindUserFromHeader;
import fr.esgi.cocotton.domain.models.recipe.Recipe;
import fr.esgi.cocotton.domain.models.recipe.RecipeDao;
import org.springframework.stereotype.Service;

@Service
public class AddRecipe {

    private final RecipeDao recipeDao;
    private final FindUserFromHeader findUserFromHeader;

    public AddRecipe(RecipeDao recipeDao, FindUserFromHeader findUserFromHeader){
        this.recipeDao = recipeDao;
        this.findUserFromHeader = findUserFromHeader;
    }

    public String execute(Recipe recipe, String token){
        recipe.setUser(findUserFromHeader.execute(token));
        return recipeDao.save(recipe);
    }
}
