package com.prueba.bounded_context.interfaces.rest.exceptionhandlers;

import com.prueba.bounded_context.domain.exceptions.*;
import com.prueba.shared.interfaces.rest.resources.MessageResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PartnerAlreadyExistsException.class)
    public ResponseEntity<MessageResource> handlePartnerAlreadyExistsException(PartnerAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new MessageResource(e.getMessage()));
    }

    @ExceptionHandler({InvalidEmailAddressException.class, InvalidPhoneNumberException.class, InvalidPersonNameException.class})
    public ResponseEntity<MessageResource> handleInvalidValueException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MessageResource(e.getMessage()));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<MessageResource> handleValidationException(ValidationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MessageResource(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageResource> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder errorMessage = new StringBuilder("Validation errors: ");
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append("; ");
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MessageResource(errorMessage.toString().trim()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResource> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new MessageResource("An unexpected error occurred: " + e.getMessage()));
    }
}
