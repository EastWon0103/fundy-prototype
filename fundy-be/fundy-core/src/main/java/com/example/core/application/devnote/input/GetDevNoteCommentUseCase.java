package com.example.core.application.devnote.input;

import com.example.core.application.devnote.input.dto.res.DevNoteCommentResponse;

import java.util.List;

public interface GetDevNoteCommentUseCase {
    List<DevNoteCommentResponse> getCommentByDevNote(long devNoteId);
}
