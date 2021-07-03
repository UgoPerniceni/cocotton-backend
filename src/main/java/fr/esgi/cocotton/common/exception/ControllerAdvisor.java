package fr.esgi.cocotton.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException exception, WebRequest request) {
        return handleExceptionInternal(exception, exception.getErrors(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException exception,
                                                                  WebRequest request) {
        log.error("Argument non valide, un problème d'unicité est apparu");
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Could not perform SQL operation");
        errors.put("error", "Constraint Violation");

        return handleExceptionInternal(exception, errors, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(ResourceOwnershipException.class)
    protected ResponseEntity<?> handleResourceOwnership(ResourceOwnershipException exception, WebRequest request) {
        return handleExceptionInternal(exception, exception.getErrors(), new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        BindingResult bindingResult = exception.getBindingResult();
        List<Map<String, String>> errors = bindingResult.getFieldErrors()
                .stream()
                .map(fieldError -> {
                    Map<String, String> json = new HashMap<>();
                    json.put("message", fieldError.getDefaultMessage());
                    json.put("objectName", fieldError.getObjectName());
                    json.put("code", fieldError.getCode());
                    json.put("field", fieldError.getField());
                    if (fieldError.getRejectedValue() != null) {
                        json.put("value", fieldError.getRejectedValue().toString());
                    }
                    return json;
                })
                .collect(Collectors.toList());

        log.error("Argument non valide");

        return handleExceptionInternal(exception, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST,
                request);
    }
}
