package com.example.fundyapi.service.user;

import com.example.fundyapi.service.user.dto.request.SignUpServiceRequest;
import com.example.fundyapi.service.user.dto.response.DuplicateNicknameResponse;

public interface UserUseCase {
    DuplicateNicknameResponse checkDuplicateNickname(final String nickname);
    long signUp(final SignUpServiceRequest request);
}
