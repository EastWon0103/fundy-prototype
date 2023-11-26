package com.example.fundyapi.security.userdetails;

import com.example.fundydomain.logic.user.FundyUserLogic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final FundyUserLogic fundyUserLogic;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return CustomUserDetails.of(fundyUserLogic.findByEmail(username).orElseThrow(() ->
            new UsernameNotFoundException("Username Not Found")));
    }
}
