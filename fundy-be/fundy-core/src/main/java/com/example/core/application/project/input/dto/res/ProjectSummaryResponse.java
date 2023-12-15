package com.example.core.application.project.input.dto.res;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProjectSummaryResponse {
    private long id;
    private String thumbnail;
    private String title;
    private int totalFundingAmount;
    private int targetAmount;
    private double percentage;
    private List<String> genres;
    private String description;
}
