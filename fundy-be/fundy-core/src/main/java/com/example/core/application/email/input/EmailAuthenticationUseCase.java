package com.example.core.application.email.input;

import com.example.core.application.email.input.dto.req.VerifyEmailServiceRequest;
import com.example.core.application.email.input.dto.res.EmailVerifyResponse;
import com.example.core.application.email.input.dto.res.IsVerifyEmailResponse;

public interface EmailAuthenticationUseCase {
    EmailVerifyResponse sendEmailVerifyCode(String email);
    IsVerifyEmailResponse isVerifyEmail(VerifyEmailServiceRequest request);
}
