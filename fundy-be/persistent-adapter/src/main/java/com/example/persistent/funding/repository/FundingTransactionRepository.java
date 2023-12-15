package com.example.persistent.funding.repository;

import com.example.persistent.funding.model.FundingTransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FundingTransactionRepository extends JpaRepository<FundingTransactionModel, Long> {
    List<FundingTransactionModel> findByRewardIdInAndIsRefund(List<Long> rewardIds, boolean isRefund);
    List<FundingTransactionModel> findByAccountIdIn(List<Long> accountIds);
}
