package com.example.fundyapi.service.devnote;

import com.example.fundyapi.service.devnote.dto.response.DevNoteDetailResponse;
import com.example.fundyapi.service.devnote.dto.response.DevNoteSummaryResponse;

import java.util.List;

public interface DevNoteUseCase {
    DevNoteDetailResponse getDevNoteDetail(long id);
    List<DevNoteSummaryResponse> getDevNotesByProjectId(long projectId);
}
