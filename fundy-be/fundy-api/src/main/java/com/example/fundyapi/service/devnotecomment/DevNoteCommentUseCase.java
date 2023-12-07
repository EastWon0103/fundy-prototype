package com.example.fundyapi.service.devnotecomment;

import com.example.fundyapi.service.devnotecomment.dto.request.SaveDevNoteCommentServiceRequest;
import com.example.fundyapi.service.devnotecomment.dto.response.DevNoteCommentResponse;

import java.util.List;

public interface DevNoteCommentUseCase {
    List<DevNoteCommentResponse> getCommentByDevNote(long devNoteId);
    long saveComment(final SaveDevNoteCommentServiceRequest request);
}
