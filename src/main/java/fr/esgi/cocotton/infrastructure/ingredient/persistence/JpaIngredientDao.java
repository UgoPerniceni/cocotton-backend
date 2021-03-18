package fr.esgi.cocotton.infrastructure.ingredient.persistence;

import fr.esgi.cocotton.domain.ingredient.Ingredient;
import fr.esgi.cocotton.domain.ingredient.IngredientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaIngredientDao implements IngredientDao {

    private final JpaIngredientRepository repository;

    @Autowired
    public JpaIngredientDao(JpaIngredientRepository repository){
        this.repository = repository;
    }

    public List<Ingredient> findAll(){
        return repository.findAll()
                .stream()
                .map(ingredient -> new Ingredient(ingredient.getName(), ingredient.getCategory()))
                .collect(Collectors.toList());
    }

    public Optional<Ingredient> findById(String id){
        Optional<JpaIngredient> jpaIngredient = repository.findById(id);
        return jpaIngredient.map(ingredient -> new Ingredient(ingredient.getName(), ingredient.getCategory()));
    }

    public String save(Ingredient ingredient){
        JpaIngredient jpaIngredient = new JpaIngredient(ingredient.getName(), ingredient.getCategory());
        repository.save(jpaIngredient);
        return jpaIngredient.getId();
    }

    @Transactional
    public void deleteById(String id){
        repository.deleteById(id);
    }
}
