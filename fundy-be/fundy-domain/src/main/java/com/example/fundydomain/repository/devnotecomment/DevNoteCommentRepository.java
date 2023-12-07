package com.example.fundydomain.repository.devnotecomment;

import com.example.fundydomain.domain.devnote.DevNote;
import com.example.fundydomain.domain.devnotecomment.DevNoteComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DevNoteCommentRepository extends JpaRepository<DevNoteComment, Long> {
    List<DevNoteComment> findByDevNote(DevNote devNote);
}
