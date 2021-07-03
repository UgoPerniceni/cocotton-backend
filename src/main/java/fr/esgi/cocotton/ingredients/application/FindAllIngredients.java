package fr.esgi.cocotton.ingredients.application;

import fr.esgi.cocotton.ingredients.domain.Ingredient;
import fr.esgi.cocotton.ingredients.domain.IngredientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllIngredients {

    private final IngredientDao ingredientDao;

    public FindAllIngredients(IngredientDao ingredientDao){
        this.ingredientDao = ingredientDao;
    }

    public List<Ingredient> execute(){
        return ingredientDao.findAll();
    }

}
