package com.hiba.gestion_carriere.exception.test_exception;

public class NotFoundTestException extends RuntimeException {

    public NotFoundTestException(String testDefinitionNonTrouvé) {
        super("Test not found");
    }
}
