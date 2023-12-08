package com.example.core.application.user.input;

import com.example.core.application.user.input.dto.res.DuplicateNicknameResponse;

public interface CheckDuplicateNicknameUseCase {
    DuplicateNicknameResponse checkDuplicateNickname(final String nickname);
}
