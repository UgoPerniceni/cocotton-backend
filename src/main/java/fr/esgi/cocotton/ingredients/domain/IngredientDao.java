package fr.esgi.cocotton.ingredients.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import java.util.List;
import java.util.Optional;

public interface IngredientDao {

    List<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    String save(Ingredient ingredient);

    void deleteById(String id);

    Ingredient applyPatch(JsonPatch patch, Ingredient targetIngredient) throws JsonPatchException, JsonProcessingException;

}
