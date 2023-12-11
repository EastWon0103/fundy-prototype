package com.example.email.message;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

public interface VerifyMessageGenerator {
    MimeMessage generateEmailVerifyMessage(String email, String code) throws MessagingException;
}
