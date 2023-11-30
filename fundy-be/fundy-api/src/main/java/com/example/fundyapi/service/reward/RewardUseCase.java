package com.example.fundyapi.service.reward;

import com.example.fundyapi.service.reward.dto.response.RewardResponse;

import java.util.List;

public interface RewardUseCase {
    List<RewardResponse> findByProjectId(long projectId);
}
