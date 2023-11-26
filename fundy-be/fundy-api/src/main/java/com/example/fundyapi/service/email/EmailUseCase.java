package com.example.fundyapi.service.email;

import com.example.fundyapi.service.email.dto.request.VerifyEmailServiceRequest;
import com.example.fundyapi.service.email.dto.response.EmailVerifyResponse;
import com.example.fundyapi.service.email.dto.response.IsVerifyEmailResponse;

public interface EmailUseCase {
    EmailVerifyResponse sendEmailVerifyCode(String email);
    IsVerifyEmailResponse isVerifyEmail(VerifyEmailServiceRequest request);
}
