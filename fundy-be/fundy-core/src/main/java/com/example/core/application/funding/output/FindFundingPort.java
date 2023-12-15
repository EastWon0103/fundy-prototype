package com.example.core.application.funding.output;

import com.example.core.application.funding.output.dto.res.LoadFundingInfoResponse;

import java.util.List;
import java.util.Optional;

public interface FindFundingPort {
    List<LoadFundingInfoResponse> findByRewardIdsAndNotRefund(List<Long> rewardIds);
    List<LoadFundingInfoResponse> findByAccountIds(List<Long> accountIds);

    Optional<LoadFundingInfoResponse> findById(long id);
}
