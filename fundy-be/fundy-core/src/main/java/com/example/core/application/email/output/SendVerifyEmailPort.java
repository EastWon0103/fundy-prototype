package com.example.core.application.email.output;

public interface SendVerifyEmailPort {
    void sendEmailPort(String email, String code);
}
