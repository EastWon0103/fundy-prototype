package com.example.core.application.project.input;

import java.util.List;

public interface GetRewardInfoUseCase {
    List<Long> findByProjectIds(List<Long> projectIds);
}
