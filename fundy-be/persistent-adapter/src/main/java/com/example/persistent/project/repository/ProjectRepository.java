package com.example.persistent.project.repository;

import com.example.persistent.project.model.ProjectModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectModel, Long> {
    Page<ProjectModel> findAll(Pageable pageable);
}
