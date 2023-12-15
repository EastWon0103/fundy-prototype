package com.example.domain.common.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Money {
    private int amount;

    public static Money of(int amount) {
        if (amount < 0)
            throw new IllegalArgumentException("Money can be Positive");
        return new Money(amount);
    }

    public void plus(Money money) {
        this.amount += money.getAmount();
    }

    public void minus(Money money) {
        if(this.amount < money.getAmount())
            throw new IllegalArgumentException("Money can be Positive");

        this.amount -= money.getAmount();
    }

    public static Money sum(List<Money> moneyList) {
        int sum = 0;
        for(Money money: moneyList)
            sum += money.getAmount();

        return Money.of(sum);
    }
}
