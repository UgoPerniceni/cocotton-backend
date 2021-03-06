package fr.esgi.cocotton.recipe.infrastructure.persistence;

import fr.esgi.cocotton.common.mapper.RecipeMapper;
import fr.esgi.cocotton.recipe.domain.Recipe;
import fr.esgi.cocotton.recipe.domain.RecipeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaRecipeDao implements RecipeDao {

    private final JpaRecipeRepository repository;
    private final RecipeMapper recipeMapper;

    @Autowired
    public JpaRecipeDao(JpaRecipeRepository repository, RecipeMapper recipeMapper) {
        this.repository = repository;
        this.recipeMapper = recipeMapper;
    }


    @Override
    public List<Recipe> findAll() {
        return repository.findAll().stream().map(recipeMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Recipe> findById(String id) {
        Optional<JpaRecipe> jpaRecipe = repository.findById(id);
        return jpaRecipe.map(recipeMapper::toDomain);
    }

    @Override
    public List<Recipe> findAllByUserId(String userId) {
        return repository.findAllByUserId(userId).stream().map(recipeMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public String save(Recipe recipe) {
        JpaRecipe jpaRecipe = recipeMapper.toEntity(recipe);
        repository.save(jpaRecipe);
        return jpaRecipe.getId();
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
