package com.example.fundydomain.logic.reward;

import com.example.fundydomain.domain.reward.Reward;

import java.util.List;
import java.util.Optional;

public interface RewardLogic {
    List<Reward> findByProjectId(final long projectId);
    Optional<Reward> findById(final long id);
}
