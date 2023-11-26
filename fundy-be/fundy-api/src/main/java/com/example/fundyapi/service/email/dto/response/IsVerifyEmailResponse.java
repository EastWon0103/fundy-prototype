package com.example.fundyapi.service.email.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Schema(name = "이메일 인증 확인 Response")
public class IsVerifyEmailResponse {
    @Schema(description = "인증 이메일", example = "dongwon0103@naer.com")
    private String email;
    @Schema(description = "인증 여부", example = "true")
    private boolean isVerify;
}
