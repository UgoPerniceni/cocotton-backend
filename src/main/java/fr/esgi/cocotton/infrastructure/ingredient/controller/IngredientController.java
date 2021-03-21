package fr.esgi.cocotton.infrastructure.ingredient.controller;

import fr.esgi.cocotton.application.ingredient.Add;
import fr.esgi.cocotton.application.ingredient.Delete;
import fr.esgi.cocotton.application.ingredient.FindAll;
import fr.esgi.cocotton.application.ingredient.FindById;
import fr.esgi.cocotton.domain.ingredient.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    private final FindAll findAll;
    private final FindById findById;
    private final Add add;
    private final Delete delete;

    @Autowired
    private IngredientController(FindAll findAll, FindById findById, Add add, Delete delete){
        this.findAll = findAll;
        this.findById = findById;
        this.add = add;
        this.delete = delete;
    }

    @GetMapping
    public ResponseEntity<List<Ingredient>> findAll(){
        return new ResponseEntity<>(findAll.execute(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> findById(@PathVariable String id){
        return new ResponseEntity<>(findById.execute(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Ingredient ingredient){
        String id = add.execute(ingredient);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        delete.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
