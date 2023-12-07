package com.example.fundydomain.logic.devnotecomment;

import com.example.fundydomain.domain.devnote.DevNote;
import com.example.fundydomain.domain.devnotecomment.DevNoteComment;
import com.example.fundydomain.repository.devnotecomment.DevNoteCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DevNoteCommentLogicImpl implements DevNoteCommentLogic {
    private final DevNoteCommentRepository devNoteCommentRepository;

    @Override
    public List<DevNoteComment> findByDevNote(DevNote devNote) {
        return devNoteCommentRepository.findByDevNote(devNote);
    }

    @Override
    public long saveDevNoteComment(DevNoteComment devNoteComment) {
        return devNoteCommentRepository.save(devNoteComment).getId();
    }
}
