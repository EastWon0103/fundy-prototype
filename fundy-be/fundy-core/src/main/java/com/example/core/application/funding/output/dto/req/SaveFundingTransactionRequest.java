package com.example.core.application.funding.output.dto.req;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SaveFundingTransactionRequest {
    private long rewardId;
    private long accountId;
    private int amount;
    private boolean isRefund;
}
