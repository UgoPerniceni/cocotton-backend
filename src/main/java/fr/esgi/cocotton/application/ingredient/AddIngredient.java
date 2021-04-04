package fr.esgi.cocotton.application.ingredient;

import fr.esgi.cocotton.domain.ingredient.Ingredient;
import fr.esgi.cocotton.domain.ingredient.IngredientDao;
import org.springframework.stereotype.Service;

@Service
public class AddIngredient {

    private final IngredientDao ingredientDao;

    public AddIngredient(IngredientDao ingredientDao){
        this.ingredientDao = ingredientDao;
    }

    public String execute(Ingredient ingredient) {
        return ingredientDao.save(ingredient);
    }
}
