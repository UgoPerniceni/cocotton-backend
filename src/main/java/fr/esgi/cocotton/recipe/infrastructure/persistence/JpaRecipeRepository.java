package fr.esgi.cocotton.recipe.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaRecipeRepository extends JpaRepository<JpaRecipe, String> {

    List<JpaRecipe> findAll();

    Optional<JpaRecipe> findById(String id);

    List<JpaRecipe> findAllByUserId(String id);

}
