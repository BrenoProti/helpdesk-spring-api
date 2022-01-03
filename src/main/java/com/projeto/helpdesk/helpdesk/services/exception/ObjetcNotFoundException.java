package com.projeto.helpdesk.helpdesk.services.exception;

public class ObjetcNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ObjetcNotFoundException(String message) {
        super(message);
    }

    public ObjetcNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
