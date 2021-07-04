package fr.esgi.cocotton.beer.infrastructure.controller;

import fr.esgi.cocotton.beer.application.FindAllBeers;
import fr.esgi.cocotton.beer.application.FindBeerById;
import fr.esgi.cocotton.beer.application.FindRandomBeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(findAllBeers.execute(), HttpStatus.OK);
    }

    @GetMapping("/random")
    public ResponseEntity<?> findRandom() {
        return new ResponseEntity<>(findRandomBeer.execute(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        return new ResponseEntity<>(findBeerById.execute(id), HttpStatus.OK);
    }
}
