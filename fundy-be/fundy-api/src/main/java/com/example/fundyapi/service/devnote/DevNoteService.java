package com.example.fundyapi.service.devnote;

import com.example.fundyapi.service.devnote.dto.response.DevNoteDetailResponse;
import com.example.fundyapi.service.devnote.dto.response.DevNoteSummaryResponse;
import com.example.fundydomain.domain.devnote.DevNote;
import com.example.fundydomain.domain.project.Project;
import com.example.fundydomain.logic.devnote.DevNoteLogic;
import com.example.fundydomain.logic.project.ProjectLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DevNoteService implements DevNoteUseCase {
    private final DevNoteLogic devNoteLogic;
    private final ProjectLogic projectLogic;

    @Override
    public DevNoteDetailResponse getDevNoteDetail(long id) {
        DevNote devNote = devNoteLogic.findById(id).orElseThrow();
        return DevNoteDetailResponse.builder()
            .id(devNote.getId())
            .title(devNote.getTitle())
            .content(devNote.getContent())
            .createdAt(devNote.getCreatedAt())
            .build();
    }

    @Override
    public List<DevNoteSummaryResponse> getDevNotesByProjectId(long projectId) {
        Project project = projectLogic.findById(projectId).orElseThrow();
        List<DevNote> devNotes = devNoteLogic.findByProject(project);

        return devNotes.stream().map((devNote) -> DevNoteSummaryResponse.builder()
            .id(devNote.getId())
            .title(devNote.getTitle())
            .thumbnail(devNote.getThumbnail())
            .createdAt(devNote.getCreatedAt())
            .build()).collect(Collectors.toList());
    }
}
