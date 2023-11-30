package com.example.fundyapi.service.reward;

import com.example.fundyapi.service.reward.dto.response.RewardResponse;
import com.example.fundydomain.logic.reward.RewardLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RewardService implements RewardUseCase {
    private final RewardLogic rewardLogic;


    @Override
    public List<RewardResponse> findByProjectId(long projectId) {
        return rewardLogic.findByProjectId(projectId).stream()
            .map(reward -> RewardResponse.builder()
                .id(reward.getId())
                .title(reward.getTitle())
                .item(reward.getItem())
                .image(reward.getImage())
                .minimumPrice(reward.getMinimumPrice())
                .build())
            .collect(Collectors.toList());
    }
}
