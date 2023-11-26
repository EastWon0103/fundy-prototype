package com.example.fundyapi.service.email;

import com.example.fundyapi.common.exception.DuplicateUserException;
import com.example.fundyapi.common.utils.email.AsyncEmailSender;
import com.example.fundyapi.common.utils.email.MimeMessageGenerator;
import com.example.fundyapi.common.utils.email.request.EmailVerifyMessageRequest;
import com.example.fundyapi.common.utils.token.email.EmailVerifyTokenProvider;
import com.example.fundyapi.common.utils.token.email.dto.request.EmailVerifyRequest;
import com.example.fundyapi.service.email.dto.request.VerifyEmailServiceRequest;
import com.example.fundyapi.service.email.dto.response.EmailVerifyResponse;
import com.example.fundyapi.service.email.dto.response.IsVerifyEmailResponse;
import com.example.fundydomain.logic.user.FundyUserLogic;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService implements EmailUseCase {
    private final EmailVerifyTokenProvider emailVerifyTokenProvider;
    private final AsyncEmailSender asyncEmailSender;
    private final MimeMessageGenerator messageGenerator;
    private final FundyUserLogic fundyUserLogic;

    @Override
    public EmailVerifyResponse sendEmailVerifyCode(String email) {
        if (fundyUserLogic.findByEmail(email).isPresent())
            throw DuplicateUserException.createBasic();

        String verifyCode = RandomStringUtils.randomAlphanumeric(8);
        String token = emailVerifyTokenProvider.generateToken(email, verifyCode);
        try {
            asyncEmailSender.sendEmail(messageGenerator.generateEmailVerifyMessage(EmailVerifyMessageRequest.builder()
                .email(email)
                .code(verifyCode)
                .build()));
        } catch (MessagingException e) {
            log.warn("이메일 인증 메일 보내기 실패");
        }
        return EmailVerifyResponse.builder()
            .email(email)
            .token(token)
            .build();
    }

    @Override
    public IsVerifyEmailResponse isVerifyEmail(VerifyEmailServiceRequest request) {
        return IsVerifyEmailResponse.builder()
            .email(request.getEmail())
            .isVerify(emailVerifyTokenProvider.isVerifyCodeAndToken(EmailVerifyRequest.builder()
                .token(request.getToken())
                .code(request.getCode())
                .email(request.getEmail())
                .build()))
            .build();
    }
}
