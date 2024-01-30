package com.parquimetro.controller.exception;

public class InvalidBusinessRules extends RuntimeException {
    public InvalidBusinessRules(String message) {
        super(message);
    }
}
