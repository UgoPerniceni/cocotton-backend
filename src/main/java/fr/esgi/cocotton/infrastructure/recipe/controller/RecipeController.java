package fr.esgi.cocotton.infrastructure.recipe.controller;

import fr.esgi.cocotton.application.recipe.FindAllRecipes;
import fr.esgi.cocotton.application.recipe.AddRecipe;
import fr.esgi.cocotton.application.recipe.DeleteRecipe;
import fr.esgi.cocotton.application.recipe.FindRecipeById;
import fr.esgi.cocotton.domain.models.recipe.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    
    private final FindRecipeById findRecipeById;
    private final FindAllRecipes findAllRecipes;
    private final AddRecipe addRecipe;
    private final DeleteRecipe deleteRecipe;
    
    @Autowired
    public RecipeController(FindRecipeById findRecipeById, FindAllRecipes findAllRecipes, AddRecipe addRecipe, DeleteRecipe deleteRecipe){
        this.findRecipeById = findRecipeById;
        this.findAllRecipes = findAllRecipes;
        this.addRecipe = addRecipe;
        this.deleteRecipe = deleteRecipe;
    }

    @GetMapping
    public ResponseEntity<List<Recipe>> findAll(){
        return new ResponseEntity<>(findAllRecipes.execute(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> findById(@PathVariable String id){
        return new ResponseEntity<>(findRecipeById.execute(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestHeader("Authorization") String token, @RequestBody Recipe recipe){
        String id = addRecipe.execute(recipe, token);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        deleteRecipe.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
