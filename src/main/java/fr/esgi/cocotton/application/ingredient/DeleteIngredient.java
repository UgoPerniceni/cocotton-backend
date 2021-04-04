package fr.esgi.cocotton.application.ingredient;

import fr.esgi.cocotton.domain.models.ingredient.IngredientDao;
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
