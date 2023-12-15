package com.example.core.application.devnote.input;

import com.example.core.application.devnote.input.dto.req.SaveDevNoteCommentServiceRequest;

public interface SaveDevNoteCommentUseCase {
    long saveComment(final SaveDevNoteCommentServiceRequest request);
}
