package com.prueba.bounded_context.domain.exceptions;

public class PartnerAlreadyExistsException extends RuntimeException {
    public PartnerAlreadyExistsException(String message) {
        super(message);
    }
}
