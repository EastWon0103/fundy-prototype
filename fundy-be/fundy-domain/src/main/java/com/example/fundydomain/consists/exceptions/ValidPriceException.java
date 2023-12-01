package com.example.fundydomain.consists.exceptions;

public class ValidPriceException extends RuntimeException {
    private ValidPriceException(String message) {
        super(message);
    }

    public static ValidPriceException createBasic() {
        return new ValidPriceException("최소가격이 맞지 않습니다.");
    }
}
