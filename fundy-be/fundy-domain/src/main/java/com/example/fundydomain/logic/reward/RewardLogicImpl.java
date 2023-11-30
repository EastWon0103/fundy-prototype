package com.example.fundydomain.logic.reward;

import com.example.fundydomain.domain.project.Project;
import com.example.fundydomain.domain.reward.Reward;
import com.example.fundydomain.repository.project.ProjectRepository;
import com.example.fundydomain.repository.reward.RewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RewardLogicImpl implements RewardLogic {
    private final RewardRepository rewardRepository;
    private final ProjectRepository projectRepository;

    @Override
    public List<Reward> findByProjectId(long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow();
        return rewardRepository.findByProject(project);
    }
}
