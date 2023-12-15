package com.example.core.application.intermiddle.user;

public interface UserValidateConnector {
    boolean isExistByEmail(String email);
    boolean isAccountOwner(final String email, final long accountId);
}
