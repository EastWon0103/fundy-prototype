package com.example.core.application.intermiddle.user;

import com.example.core.application.intermiddle.user.dto.res.AccountConnectorResponse;

import java.util.List;

public interface AccountConnector {
    List<AccountConnectorResponse> findByEmail(String email);
    void depositById(long id, int amount);
    void withdrawById(long id, int amount);
}

