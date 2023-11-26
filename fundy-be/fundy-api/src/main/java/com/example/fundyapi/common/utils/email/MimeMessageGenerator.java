package com.example.fundyapi.common.utils.email;

import com.example.fundyapi.common.utils.email.request.EmailVerifyMessageRequest;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Slf4j
@Component
@RequiredArgsConstructor
public class MimeMessageGenerator {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    private MimeMessage generateMimeMeessage(String subject, String content, String email) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        message.setSubject(subject);
        message.setRecipients(Message.RecipientType.TO, email);
        message.setText(content,"utf-8", "html");
        return message;
    }

    public MimeMessage generateEmailVerifyMessage(EmailVerifyMessageRequest request) throws MessagingException {
        Context context = new Context();
        context.setVariable("email", request.getEmail());
        context.setVariable("code", request.getCode());
        String content = templateEngine.process("sendCode",context);

        return generateMimeMeessage("Fundy 이메일 인증 안내", content, request.getEmail());
    }
}