package com.example.core.utils.token.user;

import java.util.Optional;

public interface UserTokenProvider {
    String generateToken(final UserInfo userInfo);

    Optional<UserInfo> getUserInfo(final String accessToken);
    boolean isVerifyAccessToken(final String accessToken);
}
