package com.example.fundyapi.controller.project;

import com.example.fundyapi.common.response.GlobalResponse;
import com.example.fundyapi.service.project.ProjectUseCase;
import com.example.fundyapi.service.project.dto.response.ProjectDetailResponse;
import com.example.fundyapi.service.project.dto.response.ProjectPageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects")
@Tag(name = "Project", description = "Project API")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectUseCase projectUseCase;

    @GetMapping("/{id}")
    public final GlobalResponse<ProjectDetailResponse> findById(@PathVariable(name = "id") final long id) {
        return GlobalResponse.<ProjectDetailResponse>builder()
            .message("프로젝트 id로 조회")
            .result(projectUseCase.findById(id))
            .build();
    }

    @GetMapping
    public final GlobalResponse<ProjectPageResponse> rewardFindById(
        @RequestParam(name = "pageNum") final int pageNum,
        @RequestParam(name = "pageSize") final int pageSize) {
        return GlobalResponse.<ProjectPageResponse>builder()
            .message("프로젝트 id로 조회")
            .result(projectUseCase.findAll(pageNum,pageSize))
            .build();
    }
}
