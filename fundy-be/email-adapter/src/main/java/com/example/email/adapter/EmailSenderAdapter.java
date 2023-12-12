package com.example.email.adapter;

import com.example.core.application.email.output.SendVerifyEmailPort;
import com.example.email.message.VerifyMessageGenerator;
import com.example.email.sender.EmailSender;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailSenderAdapter implements SendVerifyEmailPort {
    private final VerifyMessageGenerator verifyMessageGenerator;
    private final EmailSender emailSender;

    @Override
    public void sendEmailPort(String email, String code) {
        try {
            emailSender.sendEmail(verifyMessageGenerator.generateEmailVerifyMessage(email,code));
        } catch (MessagingException e) {
            log.info("Send Email Fail");
        }

    }
}
