package org.example.maidbookingsystem.api;

import jakarta.validation.ConstraintViolationException;
import org.example.maidbookingsystem.domain.SlotUnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(SlotUnavailableException.class)
    public ResponseEntity<ApiError> handleSlotUnavailable() {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(new ApiError("SLOT_UNAVAILABLE", "This time slot is no longer available"));
    }

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<ApiError> handleConcurrentReservation() {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(new ApiError("SLOT_UNAVAILABLE", "This time slot is no longer available"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleInvalidBody(MethodArgumentNotValidException ex) {
        Map<String, String> fields = ex.getBindingResult().getFieldErrors().stream()
            .collect(java.util.stream.Collectors.toMap(
                error -> error.getField(),
                error -> error.getDefaultMessage(),
                (first, ignored) -> first
            ));

        return ResponseEntity.badRequest()
            .body(new ApiError("VALIDATION_ERROR", "Request validation failed", fields));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleInvalidParameter(ConstraintViolationException ex) {
        return ResponseEntity.badRequest()
            .body(new ApiError("VALIDATION_ERROR", ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleBadRequest(IllegalArgumentException ex) {
        return ResponseEntity.badRequest()
            .body(new ApiError("BAD_REQUEST", ex.getMessage()));
    }
}