package com.example.domain.funding;


import com.example.domain.common.vo.Money;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Funding {
    private long id;
    private long rewardId;
    private long accountId;
    private Money amount;
    private LocalDateTime transactionDateTime;
}
