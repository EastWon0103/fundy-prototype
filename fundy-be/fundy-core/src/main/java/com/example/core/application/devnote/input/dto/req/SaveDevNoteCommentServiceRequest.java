package com.example.core.application.devnote.input.dto.req;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SaveDevNoteCommentServiceRequest {
    private long devNoteId;
    private String writerEmail;

    private String content;
}
