package com.example.fundydomain.logic.devnote;

import com.example.fundydomain.domain.devnote.DevNote;
import com.example.fundydomain.domain.project.Project;

import java.util.List;
import java.util.Optional;

public interface DevNoteLogic {
    Optional<DevNote> findById(long id);
    List<DevNote> findByProject(Project project);
}
