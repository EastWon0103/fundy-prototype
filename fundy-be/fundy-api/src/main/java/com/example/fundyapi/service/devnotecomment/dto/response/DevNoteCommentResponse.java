package com.example.fundyapi.service.devnotecomment.dto.response;

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
public class DevNoteCommentResponse {
    private long id;
    private DevNoteCommentUserInfoResponse writer;
    private LocalDateTime createdAt;
    private String content;
}
