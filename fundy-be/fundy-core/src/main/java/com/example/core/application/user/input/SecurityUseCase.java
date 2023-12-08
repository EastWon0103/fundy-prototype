package com.example.core.application.user.input;


import com.example.core.application.user.input.dto.res.SecurityUserInfoResponse;

public interface SecurityUseCase {
    SecurityUserInfoResponse getSecurityUserInfo(String email);
}
