package com.example.email.sender;

import jakarta.mail.internet.MimeMessage;

public interface EmailSender {
    void sendEmail(MimeMessage message);
}
