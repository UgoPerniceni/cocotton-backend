package fr.esgi.cocotton.application.ingredient;

import fr.esgi.cocotton.domain.ingredient.IngredientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteIngredient {

    private final IngredientDao ingredientDao;
    private final FindById findById;

    @Autowired
    public DeleteIngredient(IngredientDao ingredientDao, FindById findById){
        this.ingredientDao = ingredientDao;
        this.findById = findById;
    }

    public void execute(String id){
        findById.execute(id);
        ingredientDao.deleteById(id);
    }
}
