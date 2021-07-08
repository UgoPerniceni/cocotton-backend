package fr.esgi.cocotton.common.mapper;

import fr.esgi.cocotton.recipe.domain.Recipe;
import fr.esgi.cocotton.recipe.infrastructure.persistence.JpaRecipe;
import org.springframework.stereotype.Service;

@Service
public class RecipeMapper implements ObjectMapper<Recipe, JpaRecipe> {

    @Override
    public Recipe toDomain(JpaRecipe jpaRecipe) {
        return new Recipe(jpaRecipe.getId(),
                jpaRecipe.getTitle(),
                jpaRecipe.getPeople(),
                jpaRecipe.getSteps(),
                jpaRecipe.getIngredients(),
                jpaRecipe.getUserId());
    }

    @Override
    public JpaRecipe toEntity(Recipe recipe) {
        return new JpaRecipe(recipe.getId(),
                recipe.getTitle(),
                recipe.getPeople(),
                recipe.getSteps(),
                recipe.getIngredients(),
                recipe.getUserId());
    }
}
