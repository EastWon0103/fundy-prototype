package com.example.core.user.input;

import com.example.core.user.input.dto.req.SignInServiceRequest;
import com.example.core.user.input.dto.res.SignInResponse;

public interface SignInUseCase {
    SignInResponse signIn(final SignInServiceRequest request);
}
