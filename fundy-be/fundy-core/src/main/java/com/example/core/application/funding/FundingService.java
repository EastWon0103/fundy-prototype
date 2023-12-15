package com.example.core.application.funding;

import com.example.core.application.funding.input.FundingUseCase;
import com.example.core.application.funding.input.GetFundingUseCase;
import com.example.core.application.funding.input.dto.req.FundingServiceRequest;
import com.example.core.application.funding.input.dto.res.FundingResponse;
import com.example.core.application.funding.input.dto.res.FundingSummaryResponse;
import com.example.core.application.funding.output.FindFundingPort;
import com.example.core.application.funding.output.SaveFundingPort;
import com.example.core.application.funding.output.dto.req.SaveFundingTransactionRequest;
import com.example.core.application.funding.output.dto.res.LoadFundingInfoResponse;
import com.example.core.application.intermiddle.funding.FundingAmountConnector;
import com.example.core.application.intermiddle.project.RewardInfoConnector;
import com.example.core.application.intermiddle.project.dto.res.RewardInfoConnectorResponse;
import com.example.core.application.intermiddle.user.AccountConnector;
import com.example.core.application.intermiddle.user.UserValidateConnector;
import com.example.core.application.intermiddle.user.dto.res.AccountConnectorResponse;
import com.example.core.utils.exception.CoreExceptionFactory;
import com.example.core.utils.exception.CoreExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// TODO: 펀딩 목록 가져오기
// TODO: 개발노트 가져오기
// TODO: 개발노트 댓글
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FundingService implements FundingUseCase, FundingAmountConnector, GetFundingUseCase {
    private final FindFundingPort findFundingPort;
    private final SaveFundingPort saveFundingPort;
    private final UserValidateConnector userValidateConnector;
    private final RewardInfoConnector rewardInfoConnector;
    private final AccountConnector accountConnector;

    @Override
    public int findByRewardIds(List<Long> rewardIds) {
        return findFundingPort.findByRewardIdsAndNotRefund(rewardIds).stream().mapToInt(LoadFundingInfoResponse::getAmount).sum();
    }

    @Transactional
    @Override
    public FundingResponse funding(final FundingServiceRequest request) {
        if (!userValidateConnector.isAccountOwner(request.getEmail(), request.getAccountId()))
            throw CoreExceptionFactory.createBasic(CoreExceptionType.INCORRECT_DATA);

        if (rewardInfoConnector.findById(request.getRewardId()).getMinimumPrice() > request.getAmount())
            throw CoreExceptionFactory.createBasic(CoreExceptionType.INCORRECT_DATA);

        accountConnector.withdrawById(request.getAccountId(), request.getAmount());

        LoadFundingInfoResponse response = saveFundingPort.saveFundingTransaction(SaveFundingTransactionRequest.builder()
                .accountId(request.getAccountId())
                .rewardId(request.getRewardId())
                .isRefund(false)
            .amount(request.getAmount())
            .build());

        return FundingResponse.builder()
            .id(response.getId())
            .transactionDate(response.getTransactionDateTime())
            .amount(request.getAmount())
            .build();
    }

    @Transactional
    @Override
    public void refunding(long fundingId, String email) {
        LoadFundingInfoResponse response = findFundingPort.findById(fundingId).orElseThrow(()
            -> CoreExceptionFactory.createBasic(CoreExceptionType.NO_OBJECT));

        if(!userValidateConnector.isAccountOwner(email, response.getAccountId()))
            throw CoreExceptionFactory.createBasic(CoreExceptionType.INCORRECT_DATA);

        if(!saveFundingPort.refund(fundingId))
            throw CoreExceptionFactory.createBasic(CoreExceptionType.INCORRECT_DATA);

        accountConnector.depositById(response.getAccountId(), response.getAmount());
    }

    @Override
    public List<FundingSummaryResponse> getFundings(String email) {
        List<AccountConnectorResponse> response = accountConnector.findByEmail(email);

        return findFundingPort.findByAccountIds(response.stream()
            .map(AccountConnectorResponse::getId)
            .collect(Collectors.toList()))
            .stream().map((funding)-> {
                RewardInfoConnectorResponse rewardInfo = rewardInfoConnector.findById(funding.getRewardId());

                return FundingSummaryResponse.builder()
                    .fundingTransactionId(funding.getId())
                    .transactionDateTime(funding.getTransactionDateTime())
                    .accountId(funding.getAccountId())
                    .rewardId(funding.getRewardId())
                    .rewardTitle(rewardInfo.getTitle())
                    .rewardImage(rewardInfo.getImage())
                    .rewardItem(rewardInfo.getItem())
                    .rewardPrice(rewardInfo.getMinimumPrice())
                    .projectId(rewardInfo.getProjectId())
                    .amount(funding.getAmount())
                    .isRefund(funding.isRefund())
                    .build();
            }).collect(Collectors.toList());
    }
}