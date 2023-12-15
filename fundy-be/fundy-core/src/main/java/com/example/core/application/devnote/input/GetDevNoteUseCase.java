package com.example.core.application.devnote.input;

import com.example.core.application.devnote.input.dto.res.DevNoteDetailResponse;
import com.example.core.application.devnote.input.dto.res.DevNoteSummaryResponse;

import java.util.List;

public interface GetDevNoteUseCase {
    DevNoteDetailResponse getDevNoteDetail(long id);
    List<DevNoteSummaryResponse> getDevNotesByProjectId(long projectId);
}
