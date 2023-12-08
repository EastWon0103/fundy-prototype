package com.example.persistent.user.model;

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

@Getter
@NoArgsConstructor
@Entity
@Table(name = "account")
public class AccountModel {
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
    private UserModel owner;

    @Builder
    private AccountModel(String number, int balance, UserModel owner) {
        this.number = number;
        this.balance = balance;
        this.owner = owner;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
