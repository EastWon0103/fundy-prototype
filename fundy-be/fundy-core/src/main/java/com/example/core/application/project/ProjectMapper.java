package com.example.core.application.project;

import com.example.core.application.project.output.dto.res.LoadProjectInfoResponse;
import com.example.domain.common.vo.Money;
import com.example.domain.project.Project;
import com.example.domain.project.Reward;
import com.example.domain.project.enums.Day;
import com.example.domain.project.enums.Genre;
import com.example.domain.project.vo.DevNoteUploadTerm;
import com.example.domain.project.vo.ProjectPeriod;

import java.util.stream.Collectors;

public class ProjectMapper {
    static Project toDomain(final LoadProjectInfoResponse projectResponse) {
        return Project.builder()
            .id(projectResponse.getId())
            .ownerId(projectResponse.getOwnerId())
            .thumbnail(projectResponse.getThumbnail())
            .title(projectResponse.getTitle())
            .depositAccountId(projectResponse.getDepositAccountId())
            .description(projectResponse.getDescription())
            .targetAmount(Money.of(projectResponse.getTargetAmount()))
            .genres(projectResponse.getGenres().stream().map(Genre::nameOf).collect(Collectors.toList()))
            .content(projectResponse.getContent())
            .devNoteUploadTerm(DevNoteUploadTerm.of(projectResponse.getWeekCycle(), Day.valueOf(projectResponse.getDay())))
            .projectPeriod(ProjectPeriod.of(projectResponse.getStartDateTime(), projectResponse.getEndDateTime()))
            .rewards(projectResponse.getRewards().stream().map((rewardInfo) -> Reward.builder()
                .id(rewardInfo.getId())
                .image(rewardInfo.getImage())
                .title(rewardInfo.getTitle())
                .item(rewardInfo.getItem())
                .minimumPrice(Money.of(rewardInfo.getMinimumPrice()))
                .build()).collect(Collectors.toList()))
            .build();
    }
}