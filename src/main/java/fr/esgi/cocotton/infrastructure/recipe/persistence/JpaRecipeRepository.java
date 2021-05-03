package fr.esgi.cocotton.infrastructure.recipe.persistence;

import fr.esgi.cocotton.infrastructure.profile.persistance.JpaProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaRecipeRepository extends JpaRepository<JpaRecipe, Integer> {
    Optional<JpaRecipe> findById(String id);
    void deleteById(String id);
    List<JpaRecipe> findAllByUser(JpaProfile user);
}
