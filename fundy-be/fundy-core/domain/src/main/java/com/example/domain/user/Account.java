package com.example.domain.user;


import com.example.domain.common.vo.Money;
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
    private Money balance;

    public static String generateRandomNumber() {
        String number = RandomStringUtils.randomNumeric(11);
        StringBuilder sb = new StringBuilder(number);
        sb.insert(5,"-");
        sb.insert(9,"-");
        return sb.toString();
    }

    public void deposit(Money money) {
        balance.plus(money);
    }

    public void withdraw(Money money) {
        balance.minus(money);
    }
}
