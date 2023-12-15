package com.example.core.application.user.output.dto.req;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateAccountBalanceRequest {
    private long id;
    private int balance;

    public static UpdateAccountBalanceRequest of(long id, int balance) {
        return new UpdateAccountBalanceRequest(id, balance);
    }
}
