package com.example.fundyapi.service.funding;

import com.example.fundyapi.service.funding.dto.request.FundingServiceRequest;
import com.example.fundyapi.service.funding.dto.response.FundingResponse;

public interface FundingUseCase {
    FundingResponse funding(final FundingServiceRequest request);
}
