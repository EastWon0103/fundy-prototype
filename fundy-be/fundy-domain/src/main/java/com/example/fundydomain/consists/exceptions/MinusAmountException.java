package com.example.fundydomain.consists.exceptions;

public class MinusAmountException extends RuntimeException {
    private MinusAmountException(String message) {
        super(message);
    }

    public static MinusAmountException createBasic() {
        return new MinusAmountException("잔고 부족");
    }
}
