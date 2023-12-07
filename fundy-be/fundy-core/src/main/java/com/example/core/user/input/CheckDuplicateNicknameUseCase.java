package com.example.core.user.input;

import com.example.core.user.input.dto.res.DuplicateNicknameResponse;

public interface CheckDuplicateNicknameUseCase {
    DuplicateNicknameResponse checkDuplicateNickname(final String nickname);
}
