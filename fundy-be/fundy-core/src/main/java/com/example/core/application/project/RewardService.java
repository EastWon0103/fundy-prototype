package com.example.core.application.project;

import com.example.core.application.intermiddle.project.RewardInfoConnector;
import com.example.core.application.intermiddle.project.dto.res.RewardInfoConnectorResponse;
import com.example.core.application.project.output.FindRewardPort;
import com.example.core.application.project.output.dto.res.LoadRewardInfoResponse;
import com.example.core.utils.exception.CoreExceptionFactory;
import com.example.core.utils.exception.CoreExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RewardService implements RewardInfoConnector {
    private final FindRewardPort findRewardPort;

    @Override
    public RewardInfoConnectorResponse findById(long rewardId) {
        LoadRewardInfoResponse response = findRewardPort.findById(rewardId).orElseThrow(()
            -> CoreExceptionFactory.createBasic(CoreExceptionType.NO_OBJECT));

        return RewardInfoConnectorResponse.builder()
            .id(response.getId())
            .title(response.getTitle())
            .image(response.getImage())
            .item(response.getItem())
            .projectId(response.getProjectId())
            .minimumPrice(response.getMinimumPrice())
            .build();
    }
}
