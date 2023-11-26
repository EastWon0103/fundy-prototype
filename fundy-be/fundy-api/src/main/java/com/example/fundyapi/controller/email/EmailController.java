package com.example.fundyapi.controller.email;

import com.example.fundyapi.common.response.GlobalResponse;
import com.example.fundyapi.controller.email.dto.request.VerifyEmailRequest;
import com.example.fundyapi.service.email.EmailService;
import com.example.fundyapi.service.email.dto.request.VerifyEmailServiceRequest;
import com.example.fundyapi.service.email.dto.response.EmailVerifyResponse;
import com.example.fundyapi.service.email.dto.response.IsVerifyEmailResponse;
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
    private final EmailService emailService;
    @Operation(summary = "이메일 인증 코드", description = "이메일 인증 코드 보내기")
    @ApiResponse(responseCode = "200", description = "성공", useReturnTypeSchema = true)
    @GetMapping("/auth-code")
    public GlobalResponse<EmailVerifyResponse> sendEmailVerifyCode(
        @Parameter(description = "인증받을 이메일", example = "dongwon0103@naver.com", required = true)
        @RequestParam(name = "email") String email) {
        return GlobalResponse.<EmailVerifyResponse>builder()
            .message("이메일 보내기 완료")
            .result(emailService.sendEmailVerifyCode(email))
            .build();
    }

    @Operation(summary = "이메일 인증", description = "이메일 인증 하기")
    @ApiResponse(responseCode = "200", description = "성공", useReturnTypeSchema = true)
    @PostMapping("/verify")
    public GlobalResponse<IsVerifyEmailResponse> isVerifyEmail(@RequestBody @Valid VerifyEmailRequest request) {
        return GlobalResponse.<IsVerifyEmailResponse>builder()
            .message("이메일 인증 확인")
            .result(emailService.isVerifyEmail(VerifyEmailServiceRequest.builder()
                .email(request.getEmail())
                .code(request.getCode())
                .token(request.getToken())
                .build()))
            .build();
    }
}
