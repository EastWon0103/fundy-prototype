package com.example.core.user.input;

import com.example.core.user.input.dto.res.UserInfoResponse;

public interface GetUserInfoUseCase {
    UserInfoResponse getUserInfo(final String email);
}
