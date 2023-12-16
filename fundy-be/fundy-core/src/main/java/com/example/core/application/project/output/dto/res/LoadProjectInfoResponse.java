package com.example.core.application.project.output.dto.res;

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
public class LoadProjectInfoResponse {
    private long id;
    private String title;
    private String content;
    private String description;
    private String thumbnail;
    private List<String> genres;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private int weekCycle;
    private String day;
    private long ownerId;
    private int targetAmount;
    private List<LoadRewardInfoResponse> rewards;
    private long depositAccountId;
}
