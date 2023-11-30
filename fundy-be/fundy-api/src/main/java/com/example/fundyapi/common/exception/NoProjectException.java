package com.example.fundyapi.common.exception;

public class NoProjectException extends RuntimeException {
    private NoProjectException(String message) { super(message);}
    public static NoProjectException createBasic() {
        return new NoProjectException("프로젝트가 존재하지 않습니다");
    }
}
