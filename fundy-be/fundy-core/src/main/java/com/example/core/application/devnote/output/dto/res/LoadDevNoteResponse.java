package com.example.core.application.devnote.output.dto.res;

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
public class LoadDevNoteResponse {
    private long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private String thumbnail;
}
