package com.example.fundyapi.service.user;

import com.example.fundyapi.service.user.dto.request.SignInServiceRequest;
import com.example.fundyapi.service.user.dto.request.SignUpServiceRequest;
import com.example.fundyapi.service.user.dto.response.DuplicateNicknameResponse;
import com.example.fundyapi.service.user.dto.response.SignInResponse;
import com.example.fundyapi.service.user.dto.response.UserInfoResponse;

public interface UserUseCase {
    DuplicateNicknameResponse checkDuplicateNickname(final String nickname);
    long signUp(final SignUpServiceRequest request);
    SignInResponse signIn(final SignInServiceRequest request);
    UserInfoResponse getUserInfo(final String email);
}
