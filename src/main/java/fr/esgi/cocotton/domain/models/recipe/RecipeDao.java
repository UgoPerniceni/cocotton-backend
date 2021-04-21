package fr.esgi.cocotton.domain.models.recipe;

import fr.esgi.cocotton.domain.models.user.User;

import java.util.List;
import java.util.Optional;

public interface RecipeDao {

    List<Recipe> findAll();

    Optional<Recipe> findById(String id);

    List<Recipe> findAllByUser(User user);

    List<Recipe> findAllByNameLike(String name);

    String save(Recipe recipe);

    void deleteById(String id);
}
