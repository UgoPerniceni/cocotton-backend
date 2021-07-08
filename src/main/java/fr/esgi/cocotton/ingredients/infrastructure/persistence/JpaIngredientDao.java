package fr.esgi.cocotton.ingredients.infrastructure.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import fr.esgi.cocotton.ingredients.domain.Ingredient;
import fr.esgi.cocotton.ingredients.domain.IngredientDao;
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

    @Override
    public Ingredient applyPatch(JsonPatch patch, Ingredient targetIngredient) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(targetIngredient, JsonNode.class));
        return objectMapper.treeToValue(patched, Ingredient.class);
    }
}
