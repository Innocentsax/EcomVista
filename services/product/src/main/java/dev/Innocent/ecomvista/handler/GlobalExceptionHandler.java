package dev.Innocent.ecomvista.handler;

import dev.Innocent.ecomvista.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductPurchaseException.class)
    public ResponseEntity<String> handleException(ProductPurchaseException exp){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exp.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleException(EntityNotFoundException exp){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exp.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException exp){
        var errors =  new HashMap<String, String>();
        exp.getBindingResult().getAllErrors().forEach(error -> {
            var fieldName = ((FieldError) error).getField();
            var errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(errors));
    }
}
