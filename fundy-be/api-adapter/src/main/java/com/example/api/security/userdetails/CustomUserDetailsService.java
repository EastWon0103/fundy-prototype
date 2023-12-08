package com.example.api.security.userdetails;

import com.example.core.application.user.input.SecurityUseCase;
import com.example.core.application.user.input.dto.res.SecurityUserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final SecurityUseCase securityUseCase;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return mapToUserDetails(username);
    }

    private UserDetails mapToUserDetails(String username) {
        SecurityUserInfoResponse response = securityUseCase.getSecurityUserInfo(username);

        return CustomUserDetails.builder()
            .email(response.getEmail())
            .password(response.getPassword())
            .authorities(Collections.singletonList(response.getRole()))
            .build();
    }
}
