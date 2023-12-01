package com.example.fundydomain.repository.funding;

import com.example.fundydomain.domain.funding.FundingTransaction;
import com.example.fundydomain.domain.reward.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FundingTransactionRepository extends JpaRepository<FundingTransaction, Long> {
    List<FundingTransaction> findByReward(Reward reward);
}
