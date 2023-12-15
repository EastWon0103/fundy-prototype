package com.example.core.application.user.output;

import com.example.core.application.user.output.dto.req.SaveAccountRequest;
import com.example.core.application.user.output.dto.req.UpdateAccountBalanceRequest;

public interface SaveAccountPort {
    long saveAccount(final SaveAccountRequest request);
    void updateAccount(final UpdateAccountBalanceRequest request);
}
