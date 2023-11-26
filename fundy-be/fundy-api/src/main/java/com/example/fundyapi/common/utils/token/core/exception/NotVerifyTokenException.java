package com.example.fundyapi.common.utils.token.core.exception;

public class NotVerifyTokenException extends RuntimeException {
    private NotVerifyTokenException(String message) {
        super(message);
    }

    public static NotVerifyTokenException createBasic() {
        return new NotVerifyTokenException("Not Verify Token");
    }
}
