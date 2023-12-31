package com.example.api.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "Exception Response")
public class GlobalExceptionResponse<T> {
    @Schema(description = "API 호출 시 처리 성공 여부", example = "false")
    private boolean success = false;
    @Schema(description = "에러 발생 시 메시지", example = "에러 메시지")
    private T message;
    @Builder
    private GlobalExceptionResponse(T message) {
        this.message = message;
    }
}
