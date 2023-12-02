package com.example.fundyapi.service.funding;

import com.example.fundyapi.common.exception.NoUserException;
import com.example.fundyapi.service.funding.dto.request.FundingServiceRequest;
import com.example.fundyapi.service.funding.dto.request.RefundingServiceRequest;
import com.example.fundyapi.service.funding.dto.response.FundingResponse;
import com.example.fundyapi.service.funding.dto.response.FundingSummaryResponse;
import com.example.fundydomain.domain.account.Account;
import com.example.fundydomain.domain.funding.FundingTransaction;
import com.example.fundydomain.domain.user.FundyUser;
import com.example.fundydomain.logic.funding.FundingTransactionLogic;
import com.example.fundydomain.logic.funding.dto.request.FundingLogicRequest;
import com.example.fundydomain.logic.user.FundyUserLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


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

    @Override
    public List<FundingSummaryResponse> getFundings(String email) {
        FundyUser fundyUser = fundyUserLogic.findByEmail(email).orElseThrow(NoUserException::createBasic);
        return fundingTransactionLogic.findByUser(fundyUser).stream().map(fundingTransaction ->
                FundingSummaryResponse.builder()
                    .fundingTransactionId(fundingTransaction.getId())
                    .projectId(fundingTransaction.getReward().getProject().getId())
                    .rewardTitle(fundingTransaction.getReward().getTitle())
                    .rewardItem(fundingTransaction.getReward().getItem())
                    .rewardImage(fundingTransaction.getReward().getImage())
                    .rewardId(fundingTransaction.getReward().getId())
                    .amount(fundingTransaction.getAmount())
                    .accountId(fundingTransaction.getAccount().getId())
                    .isRefund(fundingTransaction.isRefund())
                    .build())
            .collect(Collectors.toList());
    }

    @Override
    public void refunding(final RefundingServiceRequest request) {
         FundingTransaction fundingTransaction = fundingTransactionLogic.findById(request.getFundingTransactionId()).orElseThrow();
         if(!fundingTransaction.getAccount().getOwner().getEmail().equals(request.getEmail()))
            throw new RuntimeException("No User's Account Exception");
         fundingTransactionLogic.refunding(fundingTransaction);
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
