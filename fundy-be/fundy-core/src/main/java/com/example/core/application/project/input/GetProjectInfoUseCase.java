package com.example.core.application.project.input;

import com.example.core.application.project.input.dto.res.ProjectDetailResponse;
import com.example.core.application.project.input.dto.res.ProjectPageResponse;

public interface GetProjectInfoUseCase {
    ProjectDetailResponse findById(long id);
    ProjectPageResponse findAll(int pageNum, int pageSize);
}
