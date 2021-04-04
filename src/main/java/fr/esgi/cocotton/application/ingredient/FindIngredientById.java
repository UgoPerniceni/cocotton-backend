package fr.esgi.cocotton.application.ingredient;

import fr.esgi.cocotton.domain.models.ingredient.Ingredient;
import fr.esgi.cocotton.domain.models.ingredient.IngredientDao;
import fr.esgi.cocotton.infrastructure.common.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindIngredientById {

    private final IngredientDao ingredientDao;

    @Autowired
    public FindIngredientById(IngredientDao ingredientDao){
        this.ingredientDao = ingredientDao;
    }

    public Ingredient execute(String id) {
        return ingredientDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("ingredient", id));
    }
}
