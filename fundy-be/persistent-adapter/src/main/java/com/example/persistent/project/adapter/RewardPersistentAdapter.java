package com.example.persistent.project.adapter;

import com.example.core.application.project.output.FindRewardPort;
import com.example.core.application.project.output.dto.res.LoadRewardInfoResponse;
import com.example.persistent.project.model.RewardModel;
import com.example.persistent.project.repository.RewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RewardPersistentAdapter implements FindRewardPort {
    private final RewardRepository rewardRepository;

    @Override
    public Optional<LoadRewardInfoResponse> findById(long id) {
        RewardModel rewardModel = rewardRepository.findById(id).orElse(null);

        if(rewardModel == null)
            return Optional.empty();

        return Optional.of(LoadRewardInfoResponse.builder()
                .title(rewardModel.getTitle())
                .item(rewardModel.getItem())
                .minimumPrice(rewardModel.getMinimumPrice())
                .image(rewardModel.getImage())
                .projectId(rewardModel.getProject().getId())
                .id(rewardModel.getId())
            .build());
    }
}
