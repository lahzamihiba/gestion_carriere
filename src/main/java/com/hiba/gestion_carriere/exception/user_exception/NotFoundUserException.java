package com.hiba.gestion_carriere.exception.user_exception;

public class NotFoundUserException extends RuntimeException {
    public NotFoundUserException() {

        super("utilisateur n'existe pas");
    }
}
