package com.example.api.security.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationHandler implements GetAuthentication {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    @Override
    public Authentication getAuthentication(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            email,password);

        return authenticationManagerBuilder.getObject().authenticate(authenticationToken);
    }
}
