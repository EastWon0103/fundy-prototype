package com.example.api.controller.project;

import com.example.api.common.response.GlobalResponse;
import com.example.core.application.devnote.input.GetDevNoteUseCase;
import com.example.core.application.devnote.input.dto.res.DevNoteSummaryResponse;
import com.example.core.application.project.input.GetProjectInfoUseCase;
import com.example.core.application.project.input.dto.res.ProjectDetailResponse;
import com.example.core.application.project.input.dto.res.ProjectPageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Project", description = "프로젝트 API")
@RequestMapping("/project")
public class ProjectController {
    private final GetProjectInfoUseCase getProjectInfoUseCase;
    private final GetDevNoteUseCase getDevNoteUseCase;

    @GetMapping("/{id}")
    public final GlobalResponse<ProjectDetailResponse> findById(@PathVariable(name = "id") final long id) {
        return GlobalResponse.<ProjectDetailResponse>builder()
            .message("프로젝트 id로 조회")
            .result(getProjectInfoUseCase.findById(id))
            .build();
    }

    @GetMapping
    public final GlobalResponse<ProjectPageResponse> rewardFindById(
        @RequestParam(name = "pageNum") final int pageNum,
        @RequestParam(name = "pageSize") final int pageSize) {
        return GlobalResponse.<ProjectPageResponse>builder()
            .message("프로젝트 리스트 조회")
            .result(getProjectInfoUseCase.findAll(pageNum,pageSize))
            .build();
    }

    @GetMapping("/{projectId}/devnotes")
    public GlobalResponse<List<DevNoteSummaryResponse>> findByProjectId(@PathVariable(name = "projectId") final long projectId) {
        return GlobalResponse.<List<DevNoteSummaryResponse>>builder()
            .message("개발노트 리스트 조회")
            .result(getDevNoteUseCase.getDevNotesByProjectId(projectId))
            .build();
    }
}
