package fr.esgi.cocotton.ingredients.application;

import fr.esgi.cocotton.common.exception.ResourceNotFoundException;
import fr.esgi.cocotton.ingredients.domain.Ingredient;
import fr.esgi.cocotton.ingredients.domain.IngredientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindIngredientById {

    private final IngredientDao ingredientDao;

    public FindIngredientById(IngredientDao ingredientDao){
        this.ingredientDao = ingredientDao;
    }

    public Ingredient execute(String id) {
        return ingredientDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("ingredient", "id", id));
    }
}
