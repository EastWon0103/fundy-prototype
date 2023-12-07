package com.example.core.user.input;

import com.example.core.user.input.dto.req.SignUpServiceRequest;

public interface SignUpUseCase {
    long signUp(final SignUpServiceRequest request);
}