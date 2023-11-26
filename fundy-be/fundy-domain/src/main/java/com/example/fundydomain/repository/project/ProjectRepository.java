package com.example.fundydomain.repository.project;

import com.example.fundydomain.domain.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project save(Project project);
}
