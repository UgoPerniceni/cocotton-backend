package fr.esgi.cocotton.recipe.domain;

import java.util.List;
import java.util.Optional;

public interface RecipeDao {

    List<Recipe> findAll();
    Optional<Recipe> findById(String id);
    List<Recipe> findAllByUserId(String userId);
    String save(Recipe recipe);
    void deleteById(String id);
}
