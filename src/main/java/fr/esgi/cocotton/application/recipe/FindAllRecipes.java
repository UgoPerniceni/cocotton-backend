package fr.esgi.cocotton.application.recipe;

import fr.esgi.cocotton.domain.models.recipe.Recipe;
import fr.esgi.cocotton.domain.models.recipe.RecipeDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllRecipes {

    private final RecipeDao recipeDao;

    public FindAllRecipes(RecipeDao recipeDao){
        this.recipeDao = recipeDao;
    }

    public List<Recipe> execute() {
        return recipeDao.findAll();
    }
}
