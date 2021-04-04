package fr.esgi.cocotton.application.services.ingredient;

import fr.esgi.cocotton.domain.models.ingredient.Ingredient;
import fr.esgi.cocotton.domain.models.ingredient.IngredientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAll {

    private final IngredientDao ingredientDao;

    @Autowired
    public FindAll(IngredientDao ingredientDao){
        this.ingredientDao = ingredientDao;
    }

    public List<Ingredient> execute(){
        return ingredientDao.findAll();
    }

}
