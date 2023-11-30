package com.example.fundyapi.service.project;

import com.example.fundyapi.service.project.dto.response.ProjectDetailResponse;
import com.example.fundyapi.service.project.dto.response.ProjectPageResponse;

public interface ProjectUseCase {
    ProjectDetailResponse findById(long id);
    ProjectPageResponse findAll(int pageNum, int pageSize);
}
