package com.example.api.controller.email;

import com.example.api.common.response.GlobalResponse;
import com.example.api.controller.email.request.VerifyEmailRequest;
import com.example.core.application.email.input.EmailAuthenticationUseCase;
import com.example.core.application.email.input.dto.req.VerifyEmailServiceRequest;
import com.example.core.application.email.input.dto.res.EmailVerifyResponse;
import com.example.core.application.email.input.dto.res.IsVerifyEmailResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "Email", description = "Email API")
@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {
    private final EmailAuthenticationUseCase emailAuthenticationUseCase;

    @Operation(summary = "이메일 인증 코드", description = "이메일 인증 코드 보내기")
    @ApiResponse(responseCode = "200", description = "성공", useReturnTypeSchema = true)
    @GetMapping("/auth-code")
    public GlobalResponse<EmailVerifyResponse> sendEmailVerifyCode(
        @Parameter(description = "인증받을 이메일", example = "dongwon0103@naver.com", required = true)
        @RequestParam(name = "email") String email) {
        return GlobalResponse.<EmailVerifyResponse>builder()
            .message("이메일 보내기 완료")
            .result(emailAuthenticationUseCase.sendEmailVerifyCode(email))
            .build();
    }

    @Operation(summary = "이메일 인증", description = "이메일 인증 하기")
    @ApiResponse(responseCode = "200", description = "성공", useReturnTypeSchema = true)
    @PostMapping("/verify")
    public GlobalResponse<IsVerifyEmailResponse> isVerifyEmail(@RequestBody @Valid VerifyEmailRequest request) {
        return GlobalResponse.<IsVerifyEmailResponse>builder()
            .message("이메일 인증 확인")
            .result(emailAuthenticationUseCase.isVerifyEmail(VerifyEmailServiceRequest.builder()
                .email(request.getEmail())
                .code(request.getCode())
                .token(request.getToken())
                .build()))
            .build();
    }
}
