package com.example.persistent.funding.adapter;

import com.example.core.application.funding.output.FindFundingPort;
import com.example.core.application.funding.output.SaveFundingPort;
import com.example.core.application.funding.output.dto.req.SaveFundingTransactionRequest;
import com.example.core.application.funding.output.dto.res.LoadFundingInfoResponse;
import com.example.persistent.funding.model.FundingTransactionModel;
import com.example.persistent.funding.repository.FundingTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FundingPersistentAdapter implements FindFundingPort, SaveFundingPort {
    private final FundingTransactionRepository fundingTransactionRepository;

    @Override
    public List<LoadFundingInfoResponse> findByRewardIdsAndNotRefund(List<Long> rewardIds) {
        return fundingTransactionRepository.findByRewardIdInAndIsRefund(rewardIds, false).stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<LoadFundingInfoResponse> findByAccountIds(List<Long> accountIds) {
        return fundingTransactionRepository.findByAccountIdIn(accountIds)
            .stream().map(this::mapToDto)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<LoadFundingInfoResponse> findById(long id) {
        FundingTransactionModel fundingTransactionModel = fundingTransactionRepository.findById(id).orElse(null);
        if (fundingTransactionModel == null)
            return Optional.empty();

        return Optional.of(mapToDto(fundingTransactionModel));
    }

    private LoadFundingInfoResponse mapToDto(FundingTransactionModel fundingTransactionModel) {
        return LoadFundingInfoResponse.builder()
            .id(fundingTransactionModel.getId())
            .transactionDateTime(fundingTransactionModel.getTransactionDateTime())
            .rewardId(fundingTransactionModel.getRewardId())
            .isRefund(fundingTransactionModel.isRefund())
            .accountId(fundingTransactionModel.getAccountId())
            .amount(fundingTransactionModel.getAmount())
            .build();
    }

    @Override
    public LoadFundingInfoResponse saveFundingTransaction(SaveFundingTransactionRequest request) {
        return mapToDto(fundingTransactionRepository.save(FundingTransactionModel.builder()
                .accountId(request.getAccountId())
                .transactionDateTime(LocalDateTime.now())
                .rewardId(request.getRewardId())
                .amount(request.getAmount())
                .isRefund(request.isRefund())
            .build()));
    }

    @Override
    public boolean refund(long id) {
        FundingTransactionModel fundingTransactionModel = fundingTransactionRepository.findById(id).orElse(null);
        if (fundingTransactionModel == null)
            return false;

        fundingTransactionModel.setRefund(true);
        fundingTransactionRepository.save(fundingTransactionModel);
        return true;
    }
}
