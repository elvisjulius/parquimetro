package com.parquimetro.controller.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {
    private StardardError stardardError = new StardardError();

    @ExceptionHandler(ControllerNotFoundException.class)
    public ResponseEntity<StardardError> entityNotFound(ControllerNotFoundException err, HttpServletRequest httpServletRequest) {
        HttpStatus code = HttpStatus.NOT_FOUND;
        stardardError.setTimestamp(Instant.now());
        stardardError.setStatus(code.value());
        stardardError.setError("Entity not Found");
        stardardError.setMessage(err.getMessage());
        stardardError.setPath(httpServletRequest.getRequestURI());

        return ResponseEntity.status(code).body(this.stardardError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StardardError> validation(MethodArgumentNotValidException err, HttpServletRequest httpServletRequest) {
        HttpStatus code = HttpStatus.BAD_REQUEST;
        ValidateError validateError = new ValidateError();
        validateError.setTimestamp(Instant.now());
        validateError.setStatus(code.value());
        validateError.setError("Erro de Validação");
        validateError.setMessage(err.getMessage());
        validateError.setPath(httpServletRequest.getRequestURI());
        for (FieldError f : err.getBindingResult().getFieldErrors()) {
            validateError.addMensagens(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(code).body(validateError);
    }
}