package com.example.core.application.user.output;

import com.example.core.application.user.output.dto.req.SaveAccountRequest;

public interface SaveAccountPort {
    long saveAccount(final SaveAccountRequest request);
}
