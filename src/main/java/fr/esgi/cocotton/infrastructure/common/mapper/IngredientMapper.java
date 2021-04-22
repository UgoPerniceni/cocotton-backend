package fr.esgi.cocotton.infrastructure.common.mapper;

import fr.esgi.cocotton.domain.models.ingredient.Ingredient;
import fr.esgi.cocotton.infrastructure.ingredient.persistence.JpaIngredient;

public class IngredientMapper implements ObjectMapper<Ingredient, JpaIngredient> {

    public Ingredient toDomain(JpaIngredient jpaIngredient) {
        return new Ingredient(jpaIngredient.getName(), jpaIngredient.getCategory());
    }


    public JpaIngredient toEntity(Ingredient ingredient) {
        return new JpaIngredient(ingredient.getName(), ingredient.getCategory());
    }
}
