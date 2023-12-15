package com.example.core.application.funding.output.dto.res;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoadFundingInfoResponse {
    private long id;
    private long rewardId;
    private long accountId;
    private LocalDateTime transactionDateTime;
    private boolean isRefund;
    private int amount;
}
