package com.example.core.application.project.input;

import com.example.core.application.project.input.dto.res.NotTransactedProjectResponse;
import com.example.core.application.project.input.dto.res.ProjectDetailResponse;
import com.example.core.application.project.input.dto.res.ProjectPageResponse;

import java.util.List;

public interface GetProjectInfoUseCase {
    ProjectDetailResponse findById(long id);
    ProjectPageResponse findAll(int pageNum, int pageSize);

    List<NotTransactedProjectResponse> getNotTransactedProject();
}
