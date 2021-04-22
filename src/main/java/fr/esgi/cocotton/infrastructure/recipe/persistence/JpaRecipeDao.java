package fr.esgi.cocotton.infrastructure.recipe.persistence;

import fr.esgi.cocotton.application.user.FindByIdUser;
import fr.esgi.cocotton.domain.models.recipe.RecipeDao;
import fr.esgi.cocotton.domain.models.recipe.Recipe;
import fr.esgi.cocotton.domain.models.user.User;
import fr.esgi.cocotton.infrastructure.common.exception.ResourceNotFoundException;
import fr.esgi.cocotton.infrastructure.user.persistance.JpaUser;
import fr.esgi.cocotton.infrastructure.user.persistance.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.nio.file.ReadOnlyFileSystemException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaRecipeDao implements RecipeDao{

    private final JpaRecipeRepository repository;
    private final JpaUserRepository jpaUserRepository;

    @Autowired
    public JpaRecipeDao(JpaRecipeRepository repository, JpaUserRepository jpaUserRepository){
        this.repository = repository;
        this.jpaUserRepository = jpaUserRepository;
    }

    public List<Recipe> findAll() {
        return repository.findAll()
                .stream()
                .map(recipe -> new Recipe(recipe.getId(), recipe.getName(),
                        new User(recipe.getUser().getId(), recipe.getUser().getFirstName(), recipe.getUser().getLastName(), recipe.getUser().getEmail(), recipe.getUser().getPassword(), recipe.getUser().getGender(), recipe.getUser().getBirthDate())))
                .collect(Collectors.toList());
    }

    public Optional<Recipe> findById(String id) {
        Optional<JpaRecipe> jpaRecipe = repository.findById((id));
        return jpaRecipe.map(recipe -> new Recipe(recipe.getId(), recipe.getName(),
                new User(recipe.getUser().getId(), recipe.getUser().getFirstName(), recipe.getUser().getLastName(), recipe.getUser().getEmail(), recipe.getUser().getPassword(), recipe.getUser().getGender(), recipe.getUser().getBirthDate())));
    }

    public List<Recipe> findAllByUser(User user) {
//        return repository.findAllByUser(user);
        return null;
    }

    public List<Recipe> findAllByNameLike(String name) {
//        return repository.findBy;
        return null;
    }

    public String save(Recipe recipe) {
        JpaUser jpaUser = jpaUserRepository.findById(recipe.getUser().getId()).orElseThrow(() -> new ResourceNotFoundException("user for recipe", recipe.getId()));
        JpaRecipe jpaRecipe = new JpaRecipe(recipe.getId(), recipe.getName(), jpaUser);
//        jpaUser.getR
        repository.save(jpaRecipe);
        return jpaRecipe.getId();
    }

    @Transactional
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
