package com.example.fundydomain.logic.funding;

import com.example.fundydomain.domain.funding.FundingTransaction;
import com.example.fundydomain.domain.project.Project;
import com.example.fundydomain.domain.user.FundyUser;
import com.example.fundydomain.logic.funding.dto.request.FundingLogicRequest;

import java.util.List;
import java.util.Optional;

public interface FundingTransactionLogic {
    FundingTransaction funding(FundingLogicRequest request);
    List<FundingTransaction> findByProject(Project project);
    void refunding(FundingTransaction fundingTransaction);
    List<FundingTransaction> findByUser(FundyUser fundyUser);
    Optional<FundingTransaction> findById(long id);
}
