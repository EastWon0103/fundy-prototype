package com.example.persistent.devnote.adapter;

import com.example.core.application.devnote.output.FindDevNotePort;
import com.example.core.application.devnote.output.dto.res.LoadDevNoteResponse;
import com.example.persistent.devnote.model.DevNoteModel;
import com.example.persistent.devnote.repository.DevNoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DevNotePersistentAdapter implements FindDevNotePort {
    private final DevNoteRepository devNoteRepository;

    @Override
    public List<LoadDevNoteResponse> findByProjectId(long projectId) {
        return devNoteRepository.findByProjectId(projectId).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<LoadDevNoteResponse> findById(long id) {
        DevNoteModel devNoteModel = devNoteRepository.findById(id).orElse(null);

        if (devNoteModel == null)
            return Optional.empty();

        return Optional.of(mapToDto(devNoteModel));
    }

    private LoadDevNoteResponse mapToDto(DevNoteModel devNoteModel) {
        return LoadDevNoteResponse.builder()
            .id(devNoteModel.getId())
            .thumbnail(devNoteModel.getThumbnail())
            .title(devNoteModel.getTitle())
            .content(devNoteModel.getContent())
            .createdAt(devNoteModel.getCreatedAt())
            .build();
    }
}
