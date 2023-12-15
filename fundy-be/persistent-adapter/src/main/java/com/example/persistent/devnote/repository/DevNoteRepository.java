package com.example.persistent.devnote.repository;

import com.example.persistent.devnote.model.DevNoteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DevNoteRepository extends JpaRepository<DevNoteModel, Long> {
    @Override
    Optional<DevNoteModel> findById(Long id);

    List<DevNoteModel> findByProjectId(long projectId);
}
