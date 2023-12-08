package com.example.domain.user;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.RandomStringUtils;

@Getter
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Account {
    private long id;
    private String number;
    private int balance;

    private Account(String number, int balance) {
        this.number = number;
        this.balance = balance;
    }

    public static String generateRandomNumber() {
        String number = RandomStringUtils.randomNumeric(11);
        StringBuilder sb = new StringBuilder(number);
        sb.insert(5,"-");
        sb.insert(9,"-");
        return sb.toString();
    }

    public void deposit(int amount) {
        balance+=amount;
    }

    public void withdraw(int amount) {
        if(balance<amount)
            throw new IllegalArgumentException("잔고 부족");

        balance-=amount;
    }
}
