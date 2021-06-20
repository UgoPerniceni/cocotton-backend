package fr.esgi.cocotton.ingredients.domain;

import java.util.List;
import java.util.Optional;

public interface IngredientDao {

    List<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    String save(Ingredient ingredient);

    void deleteById(String id);
}
