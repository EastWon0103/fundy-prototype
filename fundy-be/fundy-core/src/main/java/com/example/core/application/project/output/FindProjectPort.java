package com.example.core.application.project.output;

import com.example.core.application.project.output.dto.res.LoadProjectInfoResponse;
import com.example.core.application.project.output.dto.res.LoadProjectPageResponse;

import java.util.Optional;

public interface FindProjectPort {
    Optional<LoadProjectInfoResponse> findById(final long id);
    LoadProjectPageResponse findAll(int pageNum, int pageSize);
}
