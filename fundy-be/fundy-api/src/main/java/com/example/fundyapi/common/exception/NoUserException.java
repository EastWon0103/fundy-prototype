package com.example.fundyapi.common.exception;

public class NoUserException extends RuntimeException {
    private NoUserException(String message) {
        super(message);
    }

    public static NoUserException createBasic() {
        return new NoUserException("유저 존재하지 않음");
    }
}
