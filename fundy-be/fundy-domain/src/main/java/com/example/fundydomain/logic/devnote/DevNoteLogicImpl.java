package com.example.fundydomain.logic.devnote;

import com.example.fundydomain.domain.devnote.DevNote;
import com.example.fundydomain.domain.project.Project;
import com.example.fundydomain.repository.devnote.DevNoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DevNoteLogicImpl implements DevNoteLogic {
    private final DevNoteRepository devNoteRepository;
    
    @Override
    public Optional<DevNote> findById(long id) {
        return devNoteRepository.findById(id);
    }

    @Override
    public List<DevNote> findByProject(Project project) {
        return devNoteRepository.findByProject(project);
    }
}
