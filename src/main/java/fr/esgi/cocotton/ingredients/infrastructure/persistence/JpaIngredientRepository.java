package fr.esgi.cocotton.ingredients.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaIngredientRepository extends JpaRepository<JpaIngredient, Integer> {
    Optional<JpaIngredient> findById(String id);
    void deleteById(String id);
}
