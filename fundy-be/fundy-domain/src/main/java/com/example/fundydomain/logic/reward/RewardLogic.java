package com.example.fundydomain.logic.reward;

import com.example.fundydomain.domain.reward.Reward;

import java.util.List;

public interface RewardLogic {
    List<Reward> findByProjectId(final long projectId);
}
