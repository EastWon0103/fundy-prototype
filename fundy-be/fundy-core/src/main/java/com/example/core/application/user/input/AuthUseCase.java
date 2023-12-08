package com.example.core.application.user.input;

import com.example.core.application.user.input.dto.req.SignInServiceRequest;
import com.example.core.application.user.input.dto.req.SignUpServiceRequest;
import com.example.core.application.user.input.dto.res.SignInResponse;

public interface AuthUseCase {
    SignInResponse signIn(final SignInServiceRequest request);
    long signUp(final SignUpServiceRequest request);
}
