package com.example.core.application.email;

import com.example.core.application.email.input.EmailAuthenticationUseCase;
import com.example.core.application.email.input.dto.req.VerifyEmailServiceRequest;
import com.example.core.application.email.input.dto.res.EmailVerifyResponse;
import com.example.core.application.email.input.dto.res.IsVerifyEmailResponse;
import com.example.core.application.email.output.SendVerifyEmailPort;
import com.example.core.application.intermiddle.user.UserValidateConnector;
import com.example.core.utils.exception.CoreExceptionFactory;
import com.example.core.utils.exception.CoreExceptionType;
import com.example.core.utils.token.email.EmailVerifyTokenProvider;
import com.example.core.utils.token.email.request.EmailVerifyRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailAuthenticationService implements EmailAuthenticationUseCase {
    private final UserValidateConnector userValidateConnector;
    private final EmailVerifyTokenProvider emailVerifyTokenProvider;
    private final SendVerifyEmailPort sendVerifyEmailPort;

    @Override
    public EmailVerifyResponse sendEmailVerifyCode(String email) {
        if (userValidateConnector.isExistByEmail(email))
            throw CoreExceptionFactory.createBasic(CoreExceptionType.DUPLICATE_USER);

        String verifyCode = RandomStringUtils.randomAlphanumeric(8);
        String token = emailVerifyTokenProvider.generateToken(email, verifyCode);

        sendVerifyEmailPort.sendEmailPort(email, verifyCode);

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
