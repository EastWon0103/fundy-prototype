package com.example.fundydomain.domain.account;

import com.example.fundydomain.domain.user.FundyUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "ACCOUNT")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NUMBER")
    private String number;

    @Column(name = "BALANCE")
    private int balance;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    private FundyUser owner;

    @Builder
    private Account(String number, int balance, FundyUser owner) {
        this.number = number;
        this.balance = balance;
        this.owner = owner;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
