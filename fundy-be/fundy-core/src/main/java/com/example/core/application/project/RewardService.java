package com.example.core.application.project;

import com.example.core.application.intermiddle.project.RewardInfoConnector;
import com.example.core.application.intermiddle.project.dto.res.RewardInfoConnectorResponse;
import com.example.core.application.project.input.GetRewardInfoUseCase;
import com.example.core.application.project.output.FindProjectPort;
import com.example.core.application.project.output.FindRewardPort;
import com.example.core.application.project.output.dto.res.LoadProjectInfoResponse;
import com.example.core.application.project.output.dto.res.LoadRewardInfoResponse;
import com.example.core.utils.exception.CoreExceptionFactory;
import com.example.core.utils.exception.CoreExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RewardService implements RewardInfoConnector, GetRewardInfoUseCase {
    private final FindRewardPort findRewardPort;
    private final FindProjectPort findProjectPort;

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

    @Override
    public List<Long> findByProjectIds(List<Long> projectIds) {
        List<Long> rewardIds = new ArrayList<>();
        for (LoadProjectInfoResponse projectInfo: findProjectPort.findByIds(projectIds))
            rewardIds.addAll(projectInfo.getRewards().stream().map(LoadRewardInfoResponse::getId).toList());

        return rewardIds;
    }
}
