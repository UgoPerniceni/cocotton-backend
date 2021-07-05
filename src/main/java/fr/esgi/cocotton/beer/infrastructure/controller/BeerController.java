package fr.esgi.cocotton.beer.infrastructure.controller;

import fr.esgi.cocotton.beer.application.FindAllBeers;
import fr.esgi.cocotton.beer.application.FindBeerById;
import fr.esgi.cocotton.beer.application.FindRandomBeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/beers")
@RestController
public class BeerController {

    private final FindAllBeers findAllBeers;
    private final FindRandomBeer findRandomBeer;
    private final FindBeerById findBeerById;

    @Autowired
    public BeerController(FindAllBeers findAllBeers, FindRandomBeer findRandomBeer, FindBeerById findBeerById) {
        this.findAllBeers = findAllBeers;
        this.findRandomBeer = findRandomBeer;
        this.findBeerById = findBeerById;
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(findAllBeers.execute(token), HttpStatus.OK);
    }

    @GetMapping("/random")
    public ResponseEntity<?> findRandom(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(findRandomBeer.execute(token), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id, @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(findBeerById.execute(id, token), HttpStatus.OK);
    }
}
