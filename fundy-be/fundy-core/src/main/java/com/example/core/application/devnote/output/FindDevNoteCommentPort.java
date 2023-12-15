package com.example.core.application.devnote.output;

import com.example.core.application.devnote.output.dto.res.LoadDevNoteCommentResponse;

import java.util.List;

public interface FindDevNoteCommentPort {
    List<LoadDevNoteCommentResponse> findByDevNoteId(long devNoteId);
}