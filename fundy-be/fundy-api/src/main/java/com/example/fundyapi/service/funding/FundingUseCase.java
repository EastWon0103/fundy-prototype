package com.example.fundyapi.service.funding;

import com.example.fundyapi.service.funding.dto.request.FundingServiceRequest;
import com.example.fundyapi.service.funding.dto.request.RefundingServiceRequest;
import com.example.fundyapi.service.funding.dto.response.FundingResponse;
import com.example.fundyapi.service.funding.dto.response.FundingSummaryResponse;

import java.util.List;

public interface FundingUseCase {
    FundingResponse funding(final FundingServiceRequest request);
    List<FundingSummaryResponse> getFundings(final String email);
    void refunding(final RefundingServiceRequest request);
}
