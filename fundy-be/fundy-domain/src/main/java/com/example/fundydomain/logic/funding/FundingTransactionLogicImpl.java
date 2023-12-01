package com.example.fundydomain.logic.funding;

import com.example.fundydomain.consists.exceptions.MinusAmountException;
import com.example.fundydomain.consists.exceptions.ValidPriceException;
import com.example.fundydomain.domain.account.Account;
import com.example.fundydomain.domain.funding.FundingTransaction;
import com.example.fundydomain.domain.project.Project;
import com.example.fundydomain.domain.reward.Reward;
import com.example.fundydomain.logic.funding.dto.request.FundingLogicRequest;
import com.example.fundydomain.repository.account.AccountRepository;
import com.example.fundydomain.repository.funding.FundingTransactionRepository;
import com.example.fundydomain.repository.reward.RewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FundingTransactionLogicImpl implements FundingTransactionLogic {
    private final FundingTransactionRepository fundingTransactionRepository;
    private final AccountRepository accountRepository;
    private final RewardRepository rewardRepository;

    @Transactional
    @Override
    public FundingTransaction funding(FundingLogicRequest request) {
        Account account = accountRepository.findById(request.getAccountId()).orElseThrow();
        Reward reward = rewardRepository.findById(request.getRewardId()).orElseThrow();
        int balance = account.getBalance() - request.getAmount();
        if (balance < 0)
            throw MinusAmountException.createBasic();

        if (reward.getMinimumPrice() > request.getAmount())
            throw ValidPriceException.createBasic();

        account.setBalance(account.getBalance()- request.getAmount());
        accountRepository.save(account);

        FundingTransaction fundingTransaction = FundingTransaction.builder()
            .transactionDateTime(LocalDateTime.now())
            .account(account)
            .amount(request.getAmount())
            .reward(reward)
            .build();

        return fundingTransactionRepository.save(fundingTransaction);
    }

    @Override
    public List<FundingTransaction> findByProject(Project project) {
        List<Reward> rewards = rewardRepository.findByProject(project);
        List<FundingTransaction> transactions = new ArrayList<>();

        // TODO: 최적화 가능 포인트
        for(Reward reward : rewards)
            transactions.addAll(fundingTransactionRepository.findByReward(reward));

        return transactions;
    }

    @Override
    public void refunding(long transactionId) {

    }
}
