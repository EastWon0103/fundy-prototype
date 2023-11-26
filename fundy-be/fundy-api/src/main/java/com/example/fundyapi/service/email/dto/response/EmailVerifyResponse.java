package com.example.fundyapi.service.email.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(name = "이메일 인증 Response")
public class EmailVerifyResponse {
    @Schema(description = "이메일", example = "dongwon0103@naver.com")
    private String email;
    @Schema(description = "토큰", example = "kajdklfajldk;fjadklf")
    private String token;
}
