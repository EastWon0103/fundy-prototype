package com.example.core.application.devnote.output;

import com.example.core.application.devnote.output.dto.res.LoadDevNoteResponse;

import java.util.List;
import java.util.Optional;

public interface FindDevNotePort {
    List<LoadDevNoteResponse> findByProjectId(long projectId);
    Optional<LoadDevNoteResponse> findById(long id);
}
