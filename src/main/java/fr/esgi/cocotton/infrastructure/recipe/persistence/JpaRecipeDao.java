package fr.esgi.cocotton.infrastructure.recipe.persistence;

import fr.esgi.cocotton.domain.models.recipe.RecipeDao;
import fr.esgi.cocotton.domain.models.recipe.Recipe;
import fr.esgi.cocotton.domain.models.profile.Profile;
import fr.esgi.cocotton.infrastructure.profile.persistance.JpaProfileRepository;
import fr.esgi.cocotton.infrastructure.common.mapper.ProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaRecipeDao implements RecipeDao{

    private final JpaRecipeRepository repository;
    private final JpaProfileRepository jpaProfileRepository;
    private final ProfileMapper userMapper;

    @Autowired
    public JpaRecipeDao(JpaRecipeRepository repository, JpaProfileRepository jpaProfileRepository, ProfileMapper userMapper){
        this.repository = repository;
        this.jpaProfileRepository = jpaProfileRepository;
        this.userMapper = userMapper;
    }

    public List<Recipe> findAll() {
        return repository.findAll()
                .stream()
                .map(recipe -> new Recipe(recipe.getId(), recipe.getName(),
                      userMapper.toDomain(recipe.getProfile())))
                .collect(Collectors.toList());
    }

    public Optional<Recipe> findById(String id) {
        Optional<JpaRecipe> jpaRecipe = repository.findById((id));
        return jpaRecipe.map(recipe -> new Recipe(recipe.getId(), recipe.getName(),
                userMapper.toDomain(recipe.getProfile())));
    }

    public List<Recipe> findAllByUser(Profile user) {
//        return repository.findAllByUser(user);
        return null;
    }

    public List<Recipe> findAllByNameLike(String name) {
//        return repository.findBy;
        return null;
    }

    public String save(Recipe recipe) {
        JpaRecipe jpaRecipe = new JpaRecipe(recipe.getId(), recipe.getName(), userMapper.toEntity(recipe.getProfile()));
        repository.save(jpaRecipe);
        return jpaRecipe.getId();
    }

    @Transactional
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
