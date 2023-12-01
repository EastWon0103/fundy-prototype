package com.example.fundyapi.service.funding;

import com.example.fundyapi.service.funding.dto.request.FundingServiceRequest;
import com.example.fundyapi.service.funding.dto.response.FundingResponse;
import com.example.fundydomain.domain.account.Account;
import com.example.fundydomain.domain.funding.FundingTransaction;
import com.example.fundydomain.logic.funding.FundingTransactionLogic;
import com.example.fundydomain.logic.funding.dto.request.FundingLogicRequest;
import com.example.fundydomain.logic.user.FundyUserLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FundingService implements FundingUseCase {
    private final FundingTransactionLogic fundingTransactionLogic;
    private final FundyUserLogic fundyUserLogic;
    @Override
    public FundingResponse funding(FundingServiceRequest request) {
        if(!isAccountOwner(request.getEmail(), request.getAccountId()))
            throw new RuntimeException("Not Account's Owner");

        FundingTransaction fundingTransaction = fundingTransactionLogic.funding(FundingLogicRequest.builder()
                .accountId(request.getAccountId())
                .rewardId(request.getRewardId())
                .amount(request.getAmount())
            .build());

        return FundingResponse.builder()
            .id(fundingTransaction.getId())
            .transactionDate(fundingTransaction.getTransactionDateTime())
            .amount(request.getAmount())
            .build();
    }

    private boolean isAccountOwner(String email, long accountId) {
        List<Account> accounts = fundyUserLogic.findByEmail(email).orElseThrow().getAccounts();
        for(Account account : accounts) {
            if(account.getId() == accountId)
                return true;
        }
        return false;
    }
}
