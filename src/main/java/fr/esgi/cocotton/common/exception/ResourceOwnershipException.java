package fr.esgi.cocotton.common.exception;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ResourceOwnershipException extends RuntimeException {

    private final Map<String, Object> errors;

    public ResourceOwnershipException(String resource) {
        this.errors = new HashMap<>();
        this.errors.put("resource", resource);

        log.error("You can not delete this {} since you are not its owner", resource);
    }

    public Map<String, Object> getErrors() {
        return errors;
    }
}
