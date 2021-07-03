package fr.esgi.cocotton.common.exception;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ResourceNotFoundException extends RuntimeException {

    private final Map<String, Object> errors;

    public ResourceNotFoundException(String resource, String field, String value) {
        this.errors = new HashMap<>();
        this.errors.put("resource", resource);
        this.errors.put("field", field);
        this.errors.put("value", value);
        log.error("Resource {} with {} {} not found", resource, field, value);
    }

    public Map<String, Object> getErrors() {
        return errors;
    }
}
