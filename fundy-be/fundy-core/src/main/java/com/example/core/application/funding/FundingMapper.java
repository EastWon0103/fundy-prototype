package com.example.core.application.funding;

import com.example.core.application.funding.output.dto.res.LoadFundingInfoResponse;
import com.example.domain.common.vo.Money;
import com.example.domain.funding.Funding;

public class FundingMapper {
    static Funding toDomainFrom(LoadFundingInfoResponse fundingResponse) {
        return Funding.builder()
            .id(fundingResponse.getId())
            .amount(Money.of(fundingResponse.getAmount()))
            .accountId(fundingResponse.getAccountId())
            .rewardId(fundingResponse.getRewardId())
            .transactionDateTime(fundingResponse.getTransactionDateTime())
            .build();
    }
}
