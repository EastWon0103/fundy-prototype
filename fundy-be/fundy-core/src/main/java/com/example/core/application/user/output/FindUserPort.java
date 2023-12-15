package com.example.core.application.user.output;

import com.example.core.application.user.output.dto.res.LoadUserInfoResponse;

import java.util.Optional;

public interface FindUserPort {
    Optional<LoadUserInfoResponse> findByEmail(String email);
    Optional<LoadUserInfoResponse> findById(long id);
}
