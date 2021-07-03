package fr.esgi.cocotton.ingredients.application;

import fr.esgi.cocotton.ingredients.domain.IngredientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteIngredient {

    private final IngredientDao ingredientDao;
    private final FindIngredientById findIngredientById;

    @Autowired
    public DeleteIngredient(IngredientDao ingredientDao, FindIngredientById findIngredientById){
        this.ingredientDao = ingredientDao;
        this.findIngredientById = findIngredientById;
    }

    public void execute(String id){
        findIngredientById.execute(id);
        ingredientDao.deleteById(id);
    }
}
