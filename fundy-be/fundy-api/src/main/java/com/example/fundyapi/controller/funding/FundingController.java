package com.example.fundyapi.controller.funding;

import com.example.fundyapi.common.response.GlobalExceptionResponse;
import com.example.fundyapi.common.response.GlobalResponse;
import com.example.fundyapi.controller.funding.dto.request.FundingRequest;
import com.example.fundyapi.service.funding.FundingUseCase;
import com.example.fundyapi.service.funding.dto.request.FundingServiceRequest;
import com.example.fundyapi.service.funding.dto.response.FundingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/funding")
@Tag(name = "Funding", description = "Funding API")
public class FundingController {
    private final FundingUseCase fundingUseCase;

    @Operation(summary = "펀딩", description = "펀딩 하기",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "성공", useReturnTypeSchema = true)
    @ApiResponse(responseCode = "401", description = "토큰 문제",
        content = @Content(schema = @Schema(implementation = GlobalExceptionResponse.class)))
    @PostMapping
    public final GlobalResponse<FundingResponse> funding(@RequestBody final FundingRequest request, @AuthenticationPrincipal User user) {
        return GlobalResponse.<FundingResponse>builder()
            .message("펀딩 성공")
            .result(fundingUseCase.funding(FundingServiceRequest.builder()
                    .accountId(request.getAccountId())
                    .rewardId(request.getRewardId())
                    .amount(request.getAmount())
                    .email(user.getUsername())
                .build()))
            .build();
    }
}
