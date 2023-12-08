package com.example.core.application.user;

import com.example.core.application.user.output.dto.req.SaveAccountRequest;
import com.example.domain.user.Account;

public class AccountOutputMapper {
    static SaveAccountRequest domainToSaveAccountRequest(Account account, long ownerId) {
        return SaveAccountRequest.builder()
            .ownerId(ownerId)
            .number(account.getNumber())
            .balance(account.getBalance())
            .build();
    }
}
