package com.example.fundydomain.repository.devnote;

import com.example.fundydomain.domain.devnote.DevNote;
import com.example.fundydomain.domain.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DevNoteRepository extends JpaRepository<DevNote, Long> {
    @Override
    Optional<DevNote> findById(Long id);

    List<DevNote> findByProject(Project project);
}
