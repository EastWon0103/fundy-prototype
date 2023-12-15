package com.example.core.application.intermiddle.project;

import com.example.core.application.intermiddle.project.dto.res.RewardInfoConnectorResponse;

public interface RewardInfoConnector {
    RewardInfoConnectorResponse findById(long rewardId);
}
