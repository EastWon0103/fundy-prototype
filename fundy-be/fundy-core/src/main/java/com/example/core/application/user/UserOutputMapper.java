package com.example.core.application.user;

import com.example.core.application.user.input.dto.res.SecurityUserInfoResponse;
import com.example.core.application.user.input.dto.res.UserAccountsResponse;
import com.example.core.application.user.input.dto.res.UserInfoResponse;
import com.example.core.application.user.output.dto.res.LoadUserInfoResponse;
import com.example.domain.user.FundyUser;
import com.example.domain.user.enums.FundyRole;

import java.util.stream.Collectors;

public class UserOutputMapper {
    static FundyUser loadUserInfoToDomain(LoadUserInfoResponse response) {
        return FundyUser.builder()
            .id(response.getId())
            .email(response.getEmail())
            .profile(response.getProfile())
            .password(response.getPassword())
            .role(FundyRole.valueOf(response.getRole()))
            .build();
    }

    static UserInfoResponse loadUserInfoToUserInfoResponse(LoadUserInfoResponse response) {
        return UserInfoResponse.builder()
            .profile(response.getProfile())
            .nickname(response.getNickname())
            .email(response.getEmail())
            .id(response.getId())
            .accounts(response.getAccounts().stream().map((account) -> UserAccountsResponse.builder()
                    .id(account.getId())
                    .number(account.getNumber())
                    .balance(account.getBalance())
                .build())
                .collect(Collectors.toList()))
            .build();
    }

    static SecurityUserInfoResponse loadUserInfoToSecurityInfo(LoadUserInfoResponse response) {
        return SecurityUserInfoResponse.builder()
            .email(response.getEmail())
            .password(response.getPassword())
            .role(FundyRole.valueOf(response.getRole()).name())
            .build();
    }
}
