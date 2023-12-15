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
public class LoadDevNoteCommentResponse {
    private long id;
    private LocalDateTime createdAt;
    private String content;
    private long writerId;
}
