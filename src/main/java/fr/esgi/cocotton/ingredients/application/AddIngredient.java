package fr.esgi.cocotton.ingredients.application;

import fr.esgi.cocotton.ingredients.application.dto.IngredientDTO;
import fr.esgi.cocotton.ingredients.domain.Ingredient;
import fr.esgi.cocotton.ingredients.domain.IngredientDao;
import org.springframework.stereotype.Service;

@Service
public class AddIngredient {

    private final IngredientDao ingredientDao;

    public AddIngredient(IngredientDao ingredientDao){
        this.ingredientDao = ingredientDao;
    }

    public String execute(IngredientDTO ingredientDTO) {

        Ingredient ingredient = new Ingredient(ingredientDTO.getName(), ingredientDTO.getCategory());
        return ingredientDao.save(ingredient);
    }
}
