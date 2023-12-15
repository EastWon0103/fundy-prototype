package com.example.core.application.intermiddle.user;

import com.example.core.application.intermiddle.user.dto.res.UserInfoConnectorResponse;

public interface UserInfoConnector {
    UserInfoConnectorResponse searchUserById(final long id);
    UserInfoConnectorResponse searchUserByEmail(final String email);
}
