package com.example.core.utils.exception;

public class CoreExceptionFactory {

    public static CoreApplicationException createBasic(CoreExceptionType exceptionType) {
        switch (exceptionType) {
            case BASE:
                return new CoreApplicationException("Core Exception");
            case NO_USER:
                return new NoUserException("유저가 존재하지 않습니다");
            case DUPLICATE_USER:
                return new DuplicateUserException("중복된 유저가 존재합니다");
            default:
                return new CoreApplicationException("Core Exception");
        }
    }
}
