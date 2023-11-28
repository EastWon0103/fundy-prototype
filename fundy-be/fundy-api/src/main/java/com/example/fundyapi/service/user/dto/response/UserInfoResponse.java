package com.example.fundyapi.service.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "유저 정보 Response")
public class UserInfoResponse {
    private long id;
    private String email;
    private String nickname;
    private String profile;

    private List<UserAccountsResponse> accounts;
}
