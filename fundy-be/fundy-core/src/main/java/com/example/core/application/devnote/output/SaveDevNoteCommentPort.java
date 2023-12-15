package com.example.core.application.devnote.output;

import com.example.core.application.devnote.output.dto.req.SaveDevNoteCommentPersistRequest;

public interface SaveDevNoteCommentPort {
    long save(SaveDevNoteCommentPersistRequest request);
}
