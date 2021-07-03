package fr.esgi.cocotton.recipe.application;

import fr.esgi.cocotton.recipe.domain.Recipe;
import fr.esgi.cocotton.recipe.domain.RecipeDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllRecipes {

    private final RecipeDao recipeDao;

    public FindAllRecipes(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }

    public List<Recipe> execute() {
        return recipeDao.findAll();
    }
}
