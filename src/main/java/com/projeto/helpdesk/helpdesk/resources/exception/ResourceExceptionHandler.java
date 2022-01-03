package com.projeto.helpdesk.helpdesk.resources.exception;

import com.projeto.helpdesk.helpdesk.services.exception.DataIntegrityViolationException;
import com.projeto.helpdesk.helpdesk.services.exception.ObjetcNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjetcNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(ObjetcNotFoundException objExceptiion, HttpServletRequest request) {
        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Object not found", objExceptiion.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException objExceptiion, HttpServletRequest request) {
        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Dados inválidos", objExceptiion.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
