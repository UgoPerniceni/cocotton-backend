package fr.esgi.cocotton.recipe.infrastructure.controller;

import fr.esgi.cocotton.recipe.application.AddRecipe;
import fr.esgi.cocotton.recipe.application.FindAllRecipes;
import fr.esgi.cocotton.recipe.application.FindAllRecipesByUserId;
import fr.esgi.cocotton.recipe.application.FindRecipeById;
import fr.esgi.cocotton.recipe.domain.Recipe;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final FindAllRecipes findAllRecipes;
    private final FindRecipeById findRecipeById;
    private final FindAllRecipesByUserId findAllRecipesByUserId;
    private final AddRecipe addRecipe;

    public RecipeController(FindAllRecipes findAllRecipes, FindRecipeById findRecipeById, FindAllRecipesByUserId findAllRecipesByUserId, AddRecipe addRecipe) {
        this.findAllRecipes = findAllRecipes;
        this.findRecipeById = findRecipeById;
        this.findAllRecipesByUserId = findAllRecipesByUserId;
        this.addRecipe = addRecipe;
    }

    @GetMapping
    public ResponseEntity<List<Recipe>> findAll() {
        return new ResponseEntity<>(findAllRecipes.execute(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> findById(@PathVariable String id) {
        return new ResponseEntity<>(findRecipeById.execute(id), HttpStatus.OK);
    }

    @GetMapping("/profiles/{userId}")
    public ResponseEntity<List<Recipe>> findAllByUserId(@PathVariable String userId) {
        return new ResponseEntity<>(findAllRecipesByUserId.execute(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Recipe recipe) {
        String id = addRecipe.execute(recipe);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
