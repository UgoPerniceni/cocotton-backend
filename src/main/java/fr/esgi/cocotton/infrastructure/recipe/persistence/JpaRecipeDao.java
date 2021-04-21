package fr.esgi.cocotton.infrastructure.recipe.persistence;

import fr.esgi.cocotton.domain.models.recipe.RecipeDao;
import fr.esgi.cocotton.domain.models.recipe.Recipe;
import fr.esgi.cocotton.domain.models.user.User;
import fr.esgi.cocotton.infrastructure.common.exception.ResourceNotFoundException;
import fr.esgi.cocotton.infrastructure.common.mapper.UserMapper;
import fr.esgi.cocotton.infrastructure.user.persistance.JpaUser;
import fr.esgi.cocotton.infrastructure.user.persistance.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaRecipeDao implements RecipeDao{

    private final JpaRecipeRepository repository;
    private final JpaUserRepository jpaUserRepository;
    private final UserMapper userMapper;

    @Autowired
    public JpaRecipeDao(JpaRecipeRepository repository, JpaUserRepository jpaUserRepository, UserMapper userMapper){
        this.repository = repository;
        this.jpaUserRepository = jpaUserRepository;
        this.userMapper = userMapper;
    }

    public List<Recipe> findAll() {
        return repository.findAll()
                .stream()
                .map(recipe -> new Recipe(recipe.getId(), recipe.getName(),
                      userMapper.toDomain(recipe.getUser())))
                .collect(Collectors.toList());
    }

    public Optional<Recipe> findById(String id) {
        Optional<JpaRecipe> jpaRecipe = repository.findById((id));
        return jpaRecipe.map(recipe -> new Recipe(recipe.getId(), recipe.getName(),
                userMapper.toDomain(recipe.getUser())));
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
        repository.save(jpaRecipe);
        return jpaRecipe.getId();
    }

    @Transactional
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
