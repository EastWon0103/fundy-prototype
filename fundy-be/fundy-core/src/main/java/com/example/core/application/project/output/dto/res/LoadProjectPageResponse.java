package com.example.core.application.project.output.dto.res;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoadProjectPageResponse {
    private boolean hasNext;
    private List<LoadProjectInfoResponse> projects;

    public static LoadProjectPageResponse of(boolean hasNext, List<LoadProjectInfoResponse> projects) {
        return new LoadProjectPageResponse(hasNext, projects);
    }
}
