package fr.esgi.cocotton.application.ingredient;

import fr.esgi.cocotton.domain.models.ingredient.Ingredient;
import fr.esgi.cocotton.domain.models.ingredient.IngredientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllIngredients {

    private final IngredientDao ingredientDao;

    @Autowired
    public FindAllIngredients(IngredientDao ingredientDao){
        this.ingredientDao = ingredientDao;
    }

    public List<Ingredient> execute(){
        return ingredientDao.findAll();
    }

}
