package fr.esgi.cocotton.application.ingredient;

import fr.esgi.cocotton.domain.ingredient.Ingredient;
import fr.esgi.cocotton.domain.ingredient.IngredientDao;
import fr.esgi.cocotton.infrastructure.common.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindById {

    private final IngredientDao ingredientDao;

    @Autowired
    public FindById(IngredientDao ingredientDao){
        this.ingredientDao = ingredientDao;
    }

    public Ingredient execute(String id) {
        return ingredientDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("ingredient", id));
    }
}
