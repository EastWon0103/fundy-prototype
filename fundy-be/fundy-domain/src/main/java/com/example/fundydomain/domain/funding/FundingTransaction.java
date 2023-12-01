package com.example.fundydomain.domain.funding;

import com.example.fundydomain.domain.account.Account;
import com.example.fundydomain.domain.reward.Reward;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@DynamicInsert
public class FundingTransaction {
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

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "REWARD_ID")
    private Reward reward;

    @Builder
    private FundingTransaction(Account account, Reward reward, int amount, LocalDateTime transactionDateTime) {
        this.account = account;
        this.reward = reward;
        this.amount = amount;
        this.transactionDateTime = transactionDateTime;
    }
}
