package com.example.core.utils.exception;

public class DuplicateUserException extends CoreApplicationException {
    protected DuplicateUserException(String message) {
        super(message);
    }
}
