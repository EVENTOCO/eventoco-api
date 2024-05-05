package com.upao.sit.eventocoservice.exception;

public class ResourceDuplicateException extends RuntimeException {
    public ResourceDuplicateException() {
    }

    public ResourceDuplicateException(String message) {
        super(message);
    }
}
