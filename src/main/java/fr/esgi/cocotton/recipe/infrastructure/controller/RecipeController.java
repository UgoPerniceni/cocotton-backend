package fr.esgi.cocotton.recipe.infrastructure.controller;

import fr.esgi.cocotton.recipe.application.*;
import fr.esgi.cocotton.recipe.domain.Recipe;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class RecipeController {

    private final FindAllRecipes findAllRecipes;
    private final FindRecipeById findRecipeById;
    private final FindAllRecipesByUserId findAllRecipesByUserId;
    private final AddRecipe addRecipe;
    private final DeleteRecipeById deleteRecipeById;

    public RecipeController(FindAllRecipes findAllRecipes, FindRecipeById findRecipeById, FindAllRecipesByUserId findAllRecipesByUserId, AddRecipe addRecipe, DeleteRecipeById deleteRecipeById) {
        this.findAllRecipes = findAllRecipes;
        this.findRecipeById = findRecipeById;
        this.findAllRecipesByUserId = findAllRecipesByUserId;
        this.addRecipe = addRecipe;
        this.deleteRecipeById = deleteRecipeById;
    }

    @GetMapping("/api/recipes")
    public ResponseEntity<List<Recipe>> findAll() {
        return new ResponseEntity<>(findAllRecipes.execute(), HttpStatus.OK);
    }

    @GetMapping("/api/recipes/{id}")
    public ResponseEntity<Recipe> findById(@PathVariable String id) {
        return new ResponseEntity<>(findRecipeById.execute(id), HttpStatus.OK);
    }

    @GetMapping("/api/recipes/profiles/{userId}")
    public ResponseEntity<List<Recipe>> findAllByUserId(@PathVariable String userId) {
        return new ResponseEntity<>(findAllRecipesByUserId.execute(userId), HttpStatus.OK);
    }

    @PostMapping("/api/recipes")
    public ResponseEntity<?> save(@RequestBody Recipe recipe, @RequestHeader("Authorization") String token) {
        String id = addRecipe.execute(recipe, token);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/api/recipes/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        deleteRecipeById.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @MessageMapping("/api/recipes/socket")
    @SendTo("/socket/recipes")
    public ResponseEntity<?> saveWithSocket(@RequestBody Recipe recipe, @RequestHeader("Authorization") String token) {
        String id = addRecipe.execute(recipe, token);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
