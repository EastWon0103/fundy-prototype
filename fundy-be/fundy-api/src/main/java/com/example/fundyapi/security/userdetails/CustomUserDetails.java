package com.example.fundyapi.security.userdetails;

import com.example.fundydomain.domain.user.FundyUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {
    private final FundyUser fundyUser;
    private CustomUserDetails(FundyUser fundyUser) {
        this.fundyUser = fundyUser;
    }

    public static CustomUserDetails of(FundyUser fundyUser) {
        return new CustomUserDetails(fundyUser);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(fundyUser.getRole().getValue()));
    }

    @Override
    public String getPassword() {
        return fundyUser.getPassword();
    }

    @Override
    public String getUsername() {
        return fundyUser.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
