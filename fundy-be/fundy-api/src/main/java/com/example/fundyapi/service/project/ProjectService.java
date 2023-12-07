package com.example.fundyapi.service.project;

import com.example.fundyapi.common.exception.NoProjectException;
import com.example.fundyapi.service.project.dto.response.OwnerResponse;
import com.example.fundyapi.service.project.dto.response.ProjectDetailResponse;
import com.example.fundyapi.service.project.dto.response.ProjectPageResponse;
import com.example.fundyapi.service.project.dto.response.ProjectSummaryResponse;
import com.example.fundydomain.consists.enums.Genre;
import com.example.fundydomain.domain.project.Project;
import com.example.fundydomain.logic.funding.FundingTransactionLogic;
import com.example.fundydomain.logic.project.ProjectLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService implements ProjectUseCase {
    private final ProjectLogic projectLogic;
    private final FundingTransactionLogic fundingTransactionLogic;

    @Override
    public ProjectDetailResponse findById(long id) {
        Project project = projectLogic.findById(id).orElseThrow(NoProjectException::createBasic);
        int total = getTotalFundingAmount(project);
        double percentage = (double) total / (double) project.getTargetAmount() * 100;

        return ProjectDetailResponse.builder()
            .id(project.getId())
            .title(project.getTitle())
            .content(project.getContent())
            .description(project.getDescription())
            .startDateTime(project.getProjectPeriod().getStartDateTime())
            .endDateTime(project.getProjectPeriod().getEndDateTime())
            .devNoteUploadCycle(project.getDevNoteUploadTerm().getWeekCycle())
            .devNoteUploadDay(project.getDevNoteUploadTerm().getDay().getValue())
            .genres(project.getGenres()
                .stream()
                .map(Genre::getName)
                .collect(Collectors.toList()))
            .thumbnail(project.getThumbnail())
            .owner(OwnerResponse.builder()
                .id(project.getOwner().getId())
                .profile(project.getOwner().getProfile())
                .nickname(project.getOwner().getNickname())
                .build())
            .totalFundingAmount(total)
            .targetAmount(project.getTargetAmount())
            .percentage(percentage)
            .build();
    }

    @Override
    public ProjectPageResponse findAll(int pageNum, int pageSize) {
        Page<Project> projects = projectLogic.findAll(pageNum, pageSize);

        return ProjectPageResponse.builder()
            .hasNext(projects.hasNext())
            .num(projects.getNumber())
            .size(projects.getSize())
            .projectSummarys(projects.getContent().stream().map((project) -> {
                int totalFundingAmount = getTotalFundingAmount(project);
                double percentage = (double) totalFundingAmount / (double) project.getTargetAmount() * 100;

                return ProjectSummaryResponse.builder()
                    .id(project.getId())
                    .title(project.getTitle())
                    .thumbnail(project.getThumbnail())
                    .totalFundingAmount(totalFundingAmount)
                    .targetAmount(project.getTargetAmount())
                    .percentage(percentage)
                    .build();
            }).collect(Collectors.toList()))
            .build();
    }

    private int getTotalFundingAmount(Project project) {
        return fundingTransactionLogic.findByProject(project).stream().mapToInt((funding) -> {
            if (funding.isRefund())
                return 0;
            return funding.getAmount();
        }).sum();
    }
}
