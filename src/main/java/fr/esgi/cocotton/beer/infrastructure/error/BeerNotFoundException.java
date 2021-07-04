package fr.esgi.cocotton.beer.infrastructure.error;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class BeerNotFoundException extends RuntimeException {

    private final Map<String, Object> errors;

    public BeerNotFoundException() {
        this.errors = new HashMap<>();
        this.errors.put("resource", "beer");
        log.error("No beer found with this id");
    }

    public Map<String, Object> getErrors() {
        return errors;
    }
}