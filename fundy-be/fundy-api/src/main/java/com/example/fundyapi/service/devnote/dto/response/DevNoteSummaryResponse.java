package com.example.fundyapi.service.devnote.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class DevNoteSummaryResponse {
    private long id;
    private String title;
    private String thumbnail;
    private LocalDateTime createdAt;
}
