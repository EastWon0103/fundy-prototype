package com.example.persistent.devnote.adapter;

import com.example.core.application.devnote.output.FindDevNoteCommentPort;
import com.example.core.application.devnote.output.SaveDevNoteCommentPort;
import com.example.core.application.devnote.output.dto.req.SaveDevNoteCommentPersistRequest;
import com.example.core.application.devnote.output.dto.res.LoadDevNoteCommentResponse;
import com.example.persistent.devnote.model.DevNoteCommentModel;
import com.example.persistent.devnote.model.DevNoteModel;
import com.example.persistent.devnote.repository.DevNoteCommentRepository;
import com.example.persistent.devnote.repository.DevNoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DevNoteCommentPersistentAdapter implements SaveDevNoteCommentPort, FindDevNoteCommentPort {
    private final DevNoteCommentRepository devNoteCommentRepository;
    private final DevNoteRepository devNoteRepository;

    @Override
    public List<LoadDevNoteCommentResponse> findByDevNoteId(long devNoteId) {
        DevNoteModel devNoteModel = devNoteRepository.findById(devNoteId).orElse(null);

        if (devNoteModel == null)
            return new ArrayList<>();

        return devNoteModel.getComments().stream()
            .map((devNoteCommentModel) -> LoadDevNoteCommentResponse.builder()
                .id(devNoteCommentModel.getId())
                .content(devNoteCommentModel.getContent())
                .writerId(devNoteCommentModel.getWriterId())
                .createdAt(devNoteCommentModel.getCreatedAt())
                .build())
            .collect(Collectors.toList());
    }

    @Override
    public long save(SaveDevNoteCommentPersistRequest request) {
        return devNoteCommentRepository.save(DevNoteCommentModel.builder()
                .content(request.getContent())
                .createdAt(LocalDateTime.now())
                .writerId(request.getWriterId())
                .devNote(devNoteRepository.findById(request.getDevNoteId()).get())
            .build()).getId();
    }
}
