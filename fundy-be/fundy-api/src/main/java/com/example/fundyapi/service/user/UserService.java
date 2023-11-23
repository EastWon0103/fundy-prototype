package com.example.fundyapi.service.user;

import com.example.fundyapi.common.exception.DuplicateUserException;
import com.example.fundyapi.service.user.dto.request.SignUpServiceRequest;
import com.example.fundyapi.service.user.dto.response.DuplicateNicknameResponse;
import com.example.fundydomain.logic.user.FundyUserLogic;
import com.example.fundydomain.logic.user.dto.request.CreateUserLogicRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserUseCase {
    private final FundyUserLogic fundyUserLogic;
    private final PasswordEncoder passwordEncoder;

    @Override
    public DuplicateNicknameResponse checkDuplicateNickname(String nickname) {
        return DuplicateNicknameResponse.builder()
            .nickname(nickname)
            .isDuplicate(fundyUserLogic.findByNickname(nickname).isPresent())
            .build();
    }

    @Transactional
    @Override
    public long signUp(SignUpServiceRequest request) {
        if(fundyUserLogic.findByEmail(request.getEmail()).isPresent())
            throw DuplicateUserException.createBasic();

        if(fundyUserLogic.findByNickname(request.getNickname()).isPresent())
            throw DuplicateUserException.createBasic();

        return fundyUserLogic.createNormalUser(CreateUserLogicRequest.builder()
                .email(request.getEmail())
                .nickname(request.getNickname())
                .password(passwordEncoder.encode(request.getPassword()))
            .build())
            .getId();
    }
}
