package com.example.email.sender;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AsyncEmailSender implements EmailSender {
    private final JavaMailSender javaMailSender;

    @Async("ThreadPoolTaskExecutor")
    public void sendEmail(MimeMessage message) {
        javaMailSender.send(message);
    }
}
