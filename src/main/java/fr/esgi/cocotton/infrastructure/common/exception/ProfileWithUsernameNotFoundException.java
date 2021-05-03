package fr.esgi.cocotton.infrastructure.common.exception;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ProfileWithUsernameNotFoundException  extends RuntimeException {

    private final Map<String, Object> errors;

    public ProfileWithUsernameNotFoundException(String username) {
        this.errors = new HashMap<>();
        this.errors.put("username", username);
        log.error("Profile with username {} not found", username);
    }

    public Map<String, Object> getErrors() {
        return errors;
    }
}
