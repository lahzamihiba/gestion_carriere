package com.hiba.gestion_carriere.exception.user_exception;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException(String message) {
        super(
                "données invalides: " + message
        );
    }
}
