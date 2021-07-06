package fr.esgi.cocotton.recipe.application;

import fr.esgi.cocotton.common.exception.ResourceOwnershipException;
import fr.esgi.cocotton.common.security.TokenProvider;
import fr.esgi.cocotton.recipe.domain.RecipeDao;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DeleteRecipeById {

    private final RecipeDao recipeDao;
    private final FindRecipeById findRecipeById;
    public DeleteRecipeById(RecipeDao recipeDao, FindRecipeById findRecipeById) {
        this.recipeDao = recipeDao;
        this.findRecipeById = findRecipeById;
    }

    public void execute(String id) {
        findRecipeById.execute(id);
        recipeDao.deleteById(id);
    }
}
