package fr.esgi.cocotton.domain.models.recipe;

import fr.esgi.cocotton.domain.models.profile.Profile;

import java.util.List;
import java.util.Optional;

public interface RecipeDao {

    List<Recipe> findAll();

    Optional<Recipe> findById(String id);

    List<Recipe> findAllByUser(Profile user);

    List<Recipe> findAllByNameLike(String name);

    String save(Recipe recipe);

    void deleteById(String id);
}
