package com.example.core.application.project;

import com.example.core.application.intermiddle.funding.FundingAmountConnector;
import com.example.core.application.intermiddle.user.UserInfoConnector;
import com.example.core.application.intermiddle.user.dto.res.UserInfoConnectorResponse;
import com.example.core.application.project.input.GetProjectInfoUseCase;
import com.example.core.application.project.input.dto.res.OwnerResponse;
import com.example.core.application.project.input.dto.res.ProjectDetailResponse;
import com.example.core.application.project.input.dto.res.ProjectPageResponse;
import com.example.core.application.project.input.dto.res.ProjectSummaryResponse;
import com.example.core.application.project.input.dto.res.RewardDetailResponse;
import com.example.core.application.project.output.FindProjectPort;
import com.example.core.application.project.output.dto.res.LoadProjectPageResponse;
import com.example.core.utils.exception.CoreExceptionFactory;
import com.example.core.utils.exception.CoreExceptionType;
import com.example.domain.common.vo.Money;
import com.example.domain.project.Project;
import com.example.domain.project.Reward;
import com.example.domain.project.enums.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectService implements GetProjectInfoUseCase {
    private final FindProjectPort findProjectPort;
    private final UserInfoConnector userInfoConnector;
    private final FundingAmountConnector fundingAmountConnector;

    @Override
    public ProjectDetailResponse findById(long id) {
        Project project = ProjectMapper.toDomain(findProjectPort.findById(id).orElseThrow(()
                -> CoreExceptionFactory.createBasic(CoreExceptionType.NO_OBJECT)));

        UserInfoConnectorResponse owner = userInfoConnector.searchUserById(project.getOwnerId());
        Money totalFundingAmount = getTotalFundingAmount(project);

        return ProjectDetailResponse.builder()
            .id(project.getId())
            .title(project.getTitle())
            .content(project.getContent())
            .genres(project.getGenres().stream().map(Genre::getName).collect(Collectors.toList()))
            .description(project.getDescription())
            .startDateTime(project.getProjectPeriod().getStartDateTime())
            .endDateTime(project.getProjectPeriod().getEndDateTime())
            .devNoteUploadDay(project.getDevNoteUploadTerm().getDay().getValue())
            .thumbnail(project.getThumbnail())
            .owner(OwnerResponse.builder()
                .id(owner.getId())
                .nickname(owner.getNickname())
                .profile(owner.getProfile())
                .build())
            .targetAmount(project.getTargetAmount().getAmount())
            .totalFundingAmount(totalFundingAmount.getAmount())
            .percentage(project.calculatePercentage(totalFundingAmount))
            .rewards(project.getRewards().stream()
                .map((reward)-> RewardDetailResponse.builder()
                    .id(reward.getId())
                    .image(reward.getImage())
                    .title(reward.getTitle())
                    .item(reward.getItem())
                    .minimumPrice(reward.getMinimumPrice().getAmount())
                    .build()).collect(Collectors.toList()))
            .build();
    }

    @Override
    public ProjectPageResponse findAll(int pageNum, int pageSize) {
        LoadProjectPageResponse response = findProjectPort.findAll(pageNum,pageSize);
        List<Project> projects = response.getProjects().stream().map(ProjectMapper::toDomain).collect(Collectors.toList());

        return ProjectPageResponse.builder()
            .hasNext(response.isHasNext())
            .num(pageNum)
            .size(pageSize)
            .projectSummarys(projects.stream().map(this::toProjectSummary).collect(Collectors.toList()))
            .build();
    }

    private ProjectSummaryResponse toProjectSummary(Project project) {
        Money totalFundingAmount = getTotalFundingAmount(project);

        return ProjectSummaryResponse.builder()
            .id(project.getId())
            .title(project.getTitle())
            .thumbnail(project.getThumbnail())
            .description(project.getDescription())
            .targetAmount(project.getTargetAmount().getAmount())
            .genres(project.getGenres().stream().map(Genre::getName).collect(Collectors.toList()))
            .totalFundingAmount(totalFundingAmount.getAmount())
            .percentage(project.calculatePercentage(totalFundingAmount))
            .build();
    }

    private Money getTotalFundingAmount(Project project) {
        return Money.of(fundingAmountConnector.findByRewardIds(project.getRewards()
            .stream()
            .map(Reward::getId)
            .collect(Collectors.toList())));
    }
}
