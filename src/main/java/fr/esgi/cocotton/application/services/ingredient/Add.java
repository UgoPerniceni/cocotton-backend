package fr.esgi.cocotton.application.services.ingredient;

import fr.esgi.cocotton.domain.models.ingredient.Ingredient;
import fr.esgi.cocotton.domain.models.ingredient.IngredientDao;
import org.springframework.stereotype.Service;

@Service
public class Add {

    private final IngredientDao ingredientDao;

    public Add(IngredientDao ingredientDao){
        this.ingredientDao = ingredientDao;
    }

    public String execute(Ingredient ingredient) {
        return ingredientDao.save(ingredient);
    }
}
