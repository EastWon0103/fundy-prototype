package com.example.fundydomain.logic.user;

import com.example.fundydomain.domain.user.FundyUser;
import com.example.fundydomain.logic.user.dto.request.CreateUserLogicRequest;

import java.util.Optional;

public interface FundyUserLogic {
    FundyUser createNormalUser(CreateUserLogicRequest request);
    Optional<FundyUser> findByEmail(String email);
    Optional<FundyUser> findByNickname(String nickname);
}
