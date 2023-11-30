package com.example.fundydomain.logic.project;

import com.example.fundydomain.domain.project.Project;
import com.example.fundydomain.repository.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectLogicImpl implements ProjectLogic {
    private final ProjectRepository projectRepository;

    @Override
    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public Page<Project> findAll(int pageNum, int pageSize) {
        return projectRepository.findAll(PageRequest.of(pageNum,pageSize));
    }
}
