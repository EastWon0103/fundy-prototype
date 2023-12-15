package com.example.core.application.funding.input;

import com.example.core.application.funding.input.dto.req.FundingServiceRequest;
import com.example.core.application.funding.input.dto.res.FundingResponse;

public interface FundingUseCase {
    FundingResponse funding(final FundingServiceRequest request);
    void refunding(long fundingId, String email);
}
