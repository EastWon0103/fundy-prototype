package com.example.core.application.user.output.dto.res;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class LoadUserInfoResponse {
    private long id;
    private String email;
    private String nickname;
    private String password;
    private String profile;
    private int role;
    private List<LoadAccountInfoResponse> accounts;
}
