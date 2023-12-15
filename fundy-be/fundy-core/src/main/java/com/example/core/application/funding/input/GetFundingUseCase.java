package com.example.core.application.funding.input;

import com.example.core.application.funding.input.dto.res.FundingSummaryResponse;

import java.util.List;

public interface GetFundingUseCase {
    List<FundingSummaryResponse> getFundings(final String email);
}
