package com.example.fundyapi.service.funding.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FundingSummaryResponse {
    private long fundingTransactionId;
    private long projectId;
    private long rewardId;
    private long accountId;
    private String rewardTitle;
    private String rewardItem;
    private String rewardImage;
    private int amount;
    private boolean isRefund;
}
