package com.example.domain.user;

import com.example.domain.user.enums.FundyRole;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class FundyUser {
    private Long id;
    private String email;
    private String password;
    private String profile;
    private String nickname;
    private FundyRole role;
    private List<Account> accounts;
}
