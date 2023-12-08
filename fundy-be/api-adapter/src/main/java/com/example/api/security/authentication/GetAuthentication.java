package com.example.api.security.authentication;

import org.springframework.security.core.Authentication;

public interface GetAuthentication {
    Authentication getAuthentication(String email, String password);
}
