package com.example.fundyapi.controller.funding.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema
public class FundingRequest {
    private long rewardId;
    private long accountId;
    @Min(0)
    private int amount;
}
