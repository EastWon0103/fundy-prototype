package com.example.core.application.project.output;

import com.example.core.application.project.output.dto.res.LoadRewardInfoResponse;

import java.util.Optional;

public interface FindRewardPort {
    Optional<LoadRewardInfoResponse> findById(long id);
}
