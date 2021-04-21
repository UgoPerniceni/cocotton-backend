package fr.esgi.cocotton.infrastructure.recipe.persistence;

import fr.esgi.cocotton.domain.models.recipe.RecipeDao;
import fr.esgi.cocotton.domain.models.recipe.Recipe;
import fr.esgi.cocotton.domain.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaRecipeDao implements RecipeDao{

    private final JpaRecipeRepository repository;

    @Autowired
    public JpaRecipeDao(JpaRecipeRepository repository){
        this.repository = repository;
    }

    public List<Recipe> findAll() {
        return repository.findAll()
                .stream()
                .map(recipe -> new Recipe(recipe.getId(), recipe.getName(),
                        new User(recipe.getUser().getId(), recipe.getUser().getFirstName(), recipe.getUser().getLastName(), recipe.getUser().getEmail(), recipe.getUser().getPassword(), recipe.getUser().getGender(), recipe.getUser().getBirthDate())))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Recipe> findById(String id) {
        Optional<JpaRecipe> jpaRecipe = repository.findById((id));
        return jpaRecipe.map(recipe -> new Recipe(recipe.getId(), recipe.getName(),
                new User(recipe.getUser().getId(), recipe.getUser().getFirstName(), recipe.getUser().getLastName(), recipe.getUser().getEmail(), recipe.getUser().getPassword(), recipe.getUser().getGender(), recipe.getUser().getBirthDate())));
    }

    @Override
    public List<Recipe> findAllByUser(User user) {
//        return repository.findAllByUser(user);
        return null;
    }

    @Override
    public List<Recipe> findAllByNameLike(String name) {
//        return repository.findBy;
        return null;
    }

    @Override
    public String save(Recipe recipe) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }
}
