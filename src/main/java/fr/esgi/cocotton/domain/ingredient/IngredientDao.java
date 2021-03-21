package fr.esgi.cocotton.domain.ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientDao {

    List<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    String save(Ingredient ingredient);

    void deleteById(String id);
}
