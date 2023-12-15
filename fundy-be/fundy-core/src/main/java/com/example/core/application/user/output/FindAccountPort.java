package com.example.core.application.user.output;

import com.example.core.application.user.output.dto.res.LoadAccountInfoResponse;

import java.util.List;
import java.util.Optional;

public interface FindAccountPort {
    Optional<LoadAccountInfoResponse> findById(final long id);

    Optional<LoadAccountInfoResponse> findByNumber(final String number);
    List<LoadAccountInfoResponse> findByOwnerEmail(String email);
}
