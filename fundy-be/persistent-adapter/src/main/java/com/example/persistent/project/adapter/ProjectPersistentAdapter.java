package com.example.persistent.project.adapter;

import com.example.core.application.project.output.FindProjectPort;
import com.example.core.application.project.output.SaveProjectPort;
import com.example.core.application.project.output.dto.res.LoadProjectInfoResponse;
import com.example.core.application.project.output.dto.res.LoadProjectPageResponse;
import com.example.core.application.project.output.dto.res.LoadRewardInfoResponse;
import com.example.persistent.project.model.ProjectModel;
import com.example.persistent.project.model.RewardModel;
import com.example.persistent.project.repository.ProjectRepository;
import com.example.persistent.project.repository.RewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectPersistentAdapter implements FindProjectPort, SaveProjectPort {
    private final ProjectRepository projectRepository;
    private final RewardRepository rewardRepository;

    @Override
    public Optional<LoadProjectInfoResponse> findById(long id) {
        ProjectModel projectModel = projectRepository.findById(id).orElse(null);
        if (projectModel == null)
            return Optional.empty();

        return Optional.of(mapToDto(projectModel));
    }

    @Override
    public LoadProjectPageResponse findAll(int pageNum, int pageSize) {
        Page<ProjectModel> projectModelPage = projectRepository.findAll(PageRequest.of(pageNum,pageSize));

        return LoadProjectPageResponse.of(projectModelPage.hasNext(), projectModelPage.map(this::mapToDto).stream().toList());
    }

    @Override
    public List<LoadProjectInfoResponse> findByIds(List<Long> projectIds) {
        return projectRepository.findByIdIn(projectIds).stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<LoadProjectInfoResponse> findIsNotTransactionEnded() {
        return projectRepository.findByIsTransactionEnd(false).stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    private LoadProjectInfoResponse mapToDto(ProjectModel projectModel) {
        List<RewardModel> rewardModels = rewardRepository.findByProject(projectModel);

        return LoadProjectInfoResponse.builder()
            .depositAccountId(projectModel.getDepositAccountId())
            .id(projectModel.getId())
            .title(projectModel.getTitle())
            .thumbnail(projectModel.getThumbnail())
            .startDateTime(projectModel.getStartDateTime())
            .endDateTime(projectModel.getEndDateTime())
            .ownerId(projectModel.getOwnerId())
            .genres(projectModel.getGenres())
            .targetAmount(projectModel.getTargetAmount())
            .content(projectModel.getContent())
            .description(projectModel.getDescription())
            .day(projectModel.getDay())
            .weekCycle(projectModel.getWeekCycle())
            .rewards(rewardModels.stream().map((rewardModel) -> LoadRewardInfoResponse.builder()
                .id(rewardModel.getId())
                .image(rewardModel.getImage())
                .item(rewardModel.getItem())
                .minimumPrice(rewardModel.getMinimumPrice())
                .title(rewardModel.getTitle())
                .build()).collect(Collectors.toList()))
            .build();
    }

    @Override
    public boolean updateProjectExpired(long projectId) {
        ProjectModel projectModel = projectRepository.findById(projectId).orElse(null);
        if (projectModel==null)
            return false;

        projectModel.setEndDateTime(LocalDateTime.now().minusMinutes(1));
        projectRepository.save(projectModel);
        return true;
    }

    @Override
    public boolean updateProjectTransactionEnd(long projectId) {
        ProjectModel projectModel = projectRepository.findById(projectId).orElse(null);
        if (projectModel==null)
            return false;

        projectModel.setTransactionEnd(true);
        projectRepository.save(projectModel);
        return true;
    }
}