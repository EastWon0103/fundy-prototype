package com.example.core.application.funding.output;

import com.example.core.application.funding.output.dto.req.SaveFundingTransactionRequest;
import com.example.core.application.funding.output.dto.res.LoadFundingInfoResponse;

public interface SaveFundingPort {
    LoadFundingInfoResponse saveFundingTransaction(SaveFundingTransactionRequest request);
    boolean refund(long id);
}
