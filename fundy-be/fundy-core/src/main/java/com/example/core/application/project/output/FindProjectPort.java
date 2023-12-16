package com.example.core.application.project.output;

import com.example.core.application.project.output.dto.res.LoadProjectInfoResponse;
import com.example.core.application.project.output.dto.res.LoadProjectPageResponse;

import java.util.List;
import java.util.Optional;

public interface FindProjectPort {
    Optional<LoadProjectInfoResponse> findById(final long id);
    LoadProjectPageResponse findAll(int pageNum, int pageSize);
    List<LoadProjectInfoResponse> findByIds(List<Long> projectIds);
    List<LoadProjectInfoResponse> findIsNotTransactionEnded();
}
