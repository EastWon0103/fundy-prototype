package com.example.core.application.user.output;

import com.example.core.application.user.output.dto.req.SaveUserRequest;

public interface SaveUserPort {
    long saveUser(final SaveUserRequest request);
}
