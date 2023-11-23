package com.example.fundyapi.service.user.dto.response;

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
@Schema(name = "이메일 회원가입 Response")
public class DuplicateNicknameResponse {
    @Schema(description = "대상 닉네임", example = "펀디123")
    private String nickname;
    @Schema(description = "중복 여부", example = "false")
    private boolean isDuplicate;
}
