package com.example.core.application.project.input.dto.res;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ProjectDetailResponse {
    private long id;
    private String title;
    private String content;
    private String description;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private int devNoteUploadCycle;
    private String devNoteUploadDay;
    private String thumbnail;
    private List<String> genres;
    private OwnerResponse owner;
    private int totalFundingAmount;
    private int targetAmount;
    private double percentage;
    private List<RewardDetailResponse> rewards;
}
