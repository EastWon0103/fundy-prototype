package com.example.fundyapi.common.exception;

public class DuplicateUserException extends RuntimeException {
    private DuplicateUserException(String message) {
        super(message);
    }

    public static DuplicateUserException createBasic() {
        return new DuplicateUserException("유저 중복");
    }
}
