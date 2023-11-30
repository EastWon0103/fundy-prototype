package com.example.fundydomain.logic.project;

import com.example.fundydomain.domain.project.Project;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ProjectLogic {
    Optional<Project> findById(Long id);
    Page<Project> findAll(int pageNum, int pageSize);
}
