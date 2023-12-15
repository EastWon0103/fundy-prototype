package com.example.persistent.devnote.repository;

import com.example.persistent.devnote.model.DevNoteCommentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevNoteCommentRepository extends JpaRepository<DevNoteCommentModel, Long> {

}
