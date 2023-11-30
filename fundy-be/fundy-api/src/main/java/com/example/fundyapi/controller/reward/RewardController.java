package com.example.fundyapi.controller.reward;

import com.example.fundyapi.common.response.GlobalResponse;
import com.example.fundyapi.service.reward.RewardUseCase;
import com.example.fundyapi.service.reward.dto.response.RewardResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/project/{projectId}/rewards")
@Tag(name = "Reward", description = "Reward")
@RequiredArgsConstructor
public class RewardController {
    private final RewardUseCase rewardUseCase;
    @GetMapping
    public final GlobalResponse<List<RewardResponse>> rewardFindById(@PathVariable(name = "projectId") final long id) {
        return GlobalResponse.<List<RewardResponse>>builder()
            .message("프로젝트 id로 조회")
            .result(rewardUseCase.findByProjectId(id))
            .build();
    }
}
