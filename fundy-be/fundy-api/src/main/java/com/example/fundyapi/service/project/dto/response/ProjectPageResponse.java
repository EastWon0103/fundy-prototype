package com.example.fundyapi.service.project.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProjectPageResponse {
    private boolean hasNext;
    int num;
    int size;
    List<ProjectSummaryResponse> projectSummarys;
}
