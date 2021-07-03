package fr.esgi.cocotton.ingredients.infrastructure.controller;

import fr.esgi.cocotton.ingredients.application.AddIngredient;
import fr.esgi.cocotton.ingredients.application.DeleteIngredient;
import fr.esgi.cocotton.ingredients.application.FindAllIngredients;
import fr.esgi.cocotton.ingredients.application.FindIngredientById;
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

    @Autowired
    private IngredientController(FindAllIngredients findAllIngredients, FindIngredientById findIngredientById, AddIngredient addIngredient, DeleteIngredient deleteIngredient){
        this.findAllIngredients = findAllIngredients;
        this.findIngredientById = findIngredientById;
        this.addIngredient = addIngredient;
        this.deleteIngredient = deleteIngredient;
    }

    @GetMapping
    public ResponseEntity<List<Ingredient>> findAll(){
        return new ResponseEntity<>(findAllIngredients.execute(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> findById(@PathVariable String id){
        return new ResponseEntity<>(findIngredientById.execute(id), HttpStatus.OK);
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
