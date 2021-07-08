package fr.esgi.cocotton.ingredients.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import fr.esgi.cocotton.ingredients.domain.Ingredient;
import fr.esgi.cocotton.ingredients.domain.IngredientDao;
import org.springframework.stereotype.Service;

@Service
public class UpdateIngredient {

    private final IngredientDao ingredientDao;
    private final FindIngredientById findIngredientById;

    public UpdateIngredient(IngredientDao ingredientDao, FindIngredientById findIngredientById) {
        this.ingredientDao = ingredientDao;
        this.findIngredientById = findIngredientById;
    }

    public String execute(JsonPatch patch, String id) {
        Ingredient patchIngredient = findIngredientById.execute(id);
        try {
            patchIngredient =  ingredientDao.applyPatch(patch, patchIngredient);
        } catch (JsonPatchException | JsonProcessingException e) {
            e.printStackTrace();
            return id;
        }
        return ingredientDao.save(patchIngredient);
    }
}
