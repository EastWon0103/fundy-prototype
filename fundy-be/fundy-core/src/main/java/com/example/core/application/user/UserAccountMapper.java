package com.example.core.application.user;

import com.example.core.application.user.output.dto.res.LoadAccountInfoResponse;
import com.example.core.application.user.output.dto.res.LoadUserInfoResponse;
import com.example.domain.common.vo.Money;
import com.example.domain.user.Account;
import com.example.domain.user.FundyUser;
import com.example.domain.user.enums.FundyRole;

import java.util.stream.Collectors;

public class UserAccountMapper {
    static FundyUser toDomainFrom(LoadUserInfoResponse userResponse) {
        return FundyUser.builder()
            .id(userResponse.getId())
            .email(userResponse.getEmail())
            .nickname(userResponse.getNickname())
            .profile(userResponse.getProfile())
            .password(userResponse.getPassword())
            .role(FundyRole.valueOf(userResponse.getRole()))
            .accounts(userResponse.getAccounts().stream()
                .map(UserAccountMapper::toDomainFrom)
                .collect(Collectors.toList()))
            .build();
    }

    static Account toDomainFrom(LoadAccountInfoResponse accountResponse) {
        return Account.builder()
            .id(accountResponse.getId())
            .number(accountResponse.getNumber())
            .balance(Money.of(accountResponse.getBalance()))
            .build();
    }

}
