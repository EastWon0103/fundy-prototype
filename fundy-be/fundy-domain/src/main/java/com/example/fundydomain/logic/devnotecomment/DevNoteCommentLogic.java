package com.example.fundydomain.logic.devnotecomment;

import com.example.fundydomain.domain.devnote.DevNote;
import com.example.fundydomain.domain.devnotecomment.DevNoteComment;

import java.util.List;

public interface DevNoteCommentLogic {
    List<DevNoteComment> findByDevNote(DevNote devNote);
    long saveDevNoteComment(DevNoteComment devNoteComment);
}
