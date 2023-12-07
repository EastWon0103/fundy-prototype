package com.example.fundyapi.controller.devnote;

import com.example.fundyapi.common.response.GlobalResponse;
import com.example.fundyapi.service.devnote.DevNoteUseCase;
import com.example.fundyapi.service.devnote.dto.response.DevNoteDetailResponse;
import com.example.fundyapi.service.devnote.dto.response.DevNoteSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects/{projectId}/devnotes")
public class DevNoteController {
    private final DevNoteUseCase devNoteUseCase;

    @GetMapping("/{devnoteId}")
    public GlobalResponse<DevNoteDetailResponse> findById(
        @PathVariable(name = "projectId") final long projectId,
        @PathVariable(name = "devnoteId") final long devnoteId) {
        return GlobalResponse.<DevNoteDetailResponse>builder()
            .message("개발노트 조회 완료")
            .result(devNoteUseCase.getDevNoteDetail(devnoteId))
            .build();
    }

    @GetMapping
    public GlobalResponse<List<DevNoteSummaryResponse>> findByProjectId(@PathVariable(name = "projectId") final long projectId) {
        return GlobalResponse.<List<DevNoteSummaryResponse>>builder()
            .message("개발노트 리스트 조회")
            .result(devNoteUseCase.getDevNotesByProjectId(projectId))
            .build();
    }
}
