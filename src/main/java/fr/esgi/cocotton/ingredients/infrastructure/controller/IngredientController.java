package fr.esgi.cocotton.ingredients.infrastructure.controller;

import com.github.fge.jsonpatch.JsonPatch;
import fr.esgi.cocotton.ingredients.application.*;
import fr.esgi.cocotton.ingredients.application.dto.IngredientDTO;
import fr.esgi.cocotton.ingredients.domain.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    private final FindAllIngredients findAllIngredients;
    private final FindIngredientById findIngredientById;
    private final AddIngredient addIngredient;
    private final DeleteIngredient deleteIngredient;
    private final UpdateIngredient updateIngredient;

    @Autowired
    private IngredientController(FindAllIngredients findAllIngredients, FindIngredientById findIngredientById, AddIngredient addIngredient, DeleteIngredient deleteIngredient, UpdateIngredient updateIngredient){
        this.findAllIngredients = findAllIngredients;
        this.findIngredientById = findIngredientById;
        this.addIngredient = addIngredient;
        this.deleteIngredient = deleteIngredient;
        this.updateIngredient = updateIngredient;
    }

    @GetMapping
    public ResponseEntity<List<Ingredient>> findAll(){
        return new ResponseEntity<>(findAllIngredients.execute(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> findById(@PathVariable String id){
        return new ResponseEntity<>(findIngredientById.execute(id), HttpStatus.OK);
    }

    @PatchMapping(path = "{id}", consumes = "application/json-patch+json")
    public ResponseEntity<?> save(@PathVariable String id, @RequestBody JsonPatch patch) {
        updateIngredient.execute(patch, id);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid IngredientDTO ingredient){
        String id = addIngredient.execute(ingredient);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        deleteIngredient.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
