package com.example.core.application.project.input.dto.res;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class NotTransactedProjectResponse {
    private long id;
    private long depositAccountId;
    private int totalFundingAmount;
}
