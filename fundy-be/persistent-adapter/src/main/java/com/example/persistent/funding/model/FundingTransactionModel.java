package com.example.persistent.funding.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor
@Table(name = "FUNDING_TRANSACTION")
public class FundingTransactionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TRANSACTION_DATETIME")
    private LocalDateTime transactionDateTime;

    @Column(name = "amount")
    private int amount;

    @Column(name = "IS_REFUND")
    private boolean isRefund;

    @Column(name = "ACCOUNT_ID")
    private Long accountId;

    @Column(name = "REWARD_ID")
    private Long rewardId;

    @Builder
    private FundingTransactionModel(Long accountId, Long rewardId, LocalDateTime transactionDateTime, int amount, boolean isRefund) {
        this.accountId = accountId;
        this.rewardId = rewardId;
        this.transactionDateTime = transactionDateTime;
        this.amount = amount;
        this.isRefund = isRefund;
    }

    public void setRefund(boolean refund) {
        isRefund = refund;
    }
}
