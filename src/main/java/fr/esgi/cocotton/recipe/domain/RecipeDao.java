package fr.esgi.cocotton.recipe.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import java.util.List;
import java.util.Optional;

public interface RecipeDao {

    List<Recipe> findAll();
    Optional<Recipe> findById(String id);
    List<Recipe> findAllByUserId(String userId);
    String save(Recipe recipe);
    void deleteById(String id);
    Recipe applyPatch(JsonPatch patch, Recipe targetRecipe) throws JsonPatchException, JsonProcessingException;
}
