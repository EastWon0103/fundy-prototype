package com.example.core.application.intermiddle.funding;

import java.util.List;

public interface FundingAmountConnector {
    int getSumByRewardIds(List<Long> rewardIds);
}
