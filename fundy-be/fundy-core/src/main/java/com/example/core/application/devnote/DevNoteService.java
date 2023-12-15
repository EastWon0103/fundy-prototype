package com.example.core.application.devnote;

import com.example.core.application.devnote.input.GetDevNoteUseCase;
import com.example.core.application.devnote.input.dto.res.DevNoteDetailResponse;
import com.example.core.application.devnote.input.dto.res.DevNoteSummaryResponse;
import com.example.core.application.devnote.output.FindDevNotePort;
import com.example.core.application.devnote.output.dto.res.LoadDevNoteResponse;
import com.example.core.utils.exception.CoreExceptionFactory;
import com.example.core.utils.exception.CoreExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DevNoteService implements GetDevNoteUseCase {
    private final FindDevNotePort findDevNotePort;

    @Override
    public DevNoteDetailResponse getDevNoteDetail(long id) {
        LoadDevNoteResponse response = findDevNotePort.findById(id).orElseThrow(
            () -> CoreExceptionFactory.createBasic(CoreExceptionType.NO_OBJECT));

        return DevNoteDetailResponse.builder()
            .id(response.getId())
            .title(response.getTitle())
            .content(response.getContent())
            .createdAt(response.getCreatedAt())
            .build();
    }

    @Override
    public List<DevNoteSummaryResponse> getDevNotesByProjectId(long projectId) {
        return findDevNotePort.findByProjectId(projectId)
            .stream().map((devNote) -> DevNoteSummaryResponse.builder()
                .id(devNote.getId())
                .title(devNote.getTitle())
                .thumbnail(devNote.getThumbnail())
                .createdAt(devNote.getCreatedAt())
                .build())
            .collect(Collectors.toList());
    }
}
