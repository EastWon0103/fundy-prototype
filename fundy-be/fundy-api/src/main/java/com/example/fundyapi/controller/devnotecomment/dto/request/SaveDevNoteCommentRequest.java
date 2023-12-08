package com.example.fundyapi.controller.devnotecomment.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaveDevNoteCommentRequest {
    @NotNull
    private String content;
}