package com.example.core.application.user.input;

import com.example.core.application.user.input.dto.res.UserInfoResponse;

public interface GetUserInfoUseCase {
    UserInfoResponse getUserInfoByEmail(final String email);
    UserInfoResponse getUserInfoById(final Long id);
}
