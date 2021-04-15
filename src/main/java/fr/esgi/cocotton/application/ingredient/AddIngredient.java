package fr.esgi.cocotton.application.ingredient;

import fr.esgi.cocotton.domain.models.ingredient.Ingredient;
import fr.esgi.cocotton.domain.models.ingredient.IngredientDao;
import org.springframework.stereotype.Service;

@Service
public class AddIngredient implements AddIngredientUseCase {

    private final IngredientDao ingredientDao;

    public AddIngredient(IngredientDao ingredientDao){
        this.ingredientDao = ingredientDao;
    }

    public String execute(AddIngredientCommand command) {

        Ingredient ingredient = new Ingredient(command.getName(), command.getCategory());
        return ingredientDao.save(ingredient);
    }
}
