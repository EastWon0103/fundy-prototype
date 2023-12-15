package com.example.core.application.user;

import com.example.core.application.intermiddle.user.UserInfoConnector;
import com.example.core.application.intermiddle.user.UserValidateConnector;
import com.example.core.application.intermiddle.user.dto.res.UserInfoConnectorResponse;
import com.example.core.application.user.input.AuthUseCase;
import com.example.core.application.user.input.CheckDuplicateNicknameUseCase;
import com.example.core.application.user.input.GetUserInfoUseCase;
import com.example.core.application.user.input.SecurityUseCase;
import com.example.core.application.user.input.dto.req.SignInServiceRequest;
import com.example.core.application.user.input.dto.req.SignUpServiceRequest;
import com.example.core.application.user.input.dto.res.DuplicateNicknameResponse;
import com.example.core.application.user.input.dto.res.SecurityUserInfoResponse;
import com.example.core.application.user.input.dto.res.SignInResponse;
import com.example.core.application.user.input.dto.res.UserAccountsResponse;
import com.example.core.application.user.input.dto.res.UserInfoResponse;
import com.example.core.application.user.output.FindAccountPort;
import com.example.core.application.user.output.FindUserPort;
import com.example.core.application.user.output.SaveAccountPort;
import com.example.core.application.user.output.SaveUserPort;
import com.example.core.application.user.output.ValidateUserPort;
import com.example.core.application.user.output.dto.req.SaveAccountRequest;
import com.example.core.application.user.output.dto.req.SaveUserRequest;
import com.example.core.utils.exception.CoreExceptionFactory;
import com.example.core.utils.exception.CoreExceptionType;
import com.example.core.utils.token.JwtUtil;
import com.example.core.utils.token.user.UserInfo;
import com.example.core.utils.token.user.UserTokenProvider;
import com.example.domain.user.Account;
import com.example.domain.user.FundyUser;
import com.example.domain.user.enums.FundyRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;


@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class UserService implements AuthUseCase, GetUserInfoUseCase,
    SecurityUseCase, CheckDuplicateNicknameUseCase,
    UserValidateConnector, UserInfoConnector {
    private final SaveUserPort saveUserPort;
    private final SaveAccountPort saveAccountPort;
    private final FindAccountPort findAccountPort;
    private final FindUserPort findUserPort;
    private final ValidateUserPort validateUserPort;
    private final UserTokenProvider userTokenProvider;

    @Override
    public SignInResponse signIn(SignInServiceRequest request) {
        return SignInResponse.builder()
            .grantType(JwtUtil.GRANT_TYPE)
            .token(userTokenProvider.generateToken(UserInfo.builder()
                    .email(request.getEmail())
                    .authorities(request.getAuthorities())
                .build()))
            .build();
    }

    @Transactional
    @Override
    public long signUp(SignUpServiceRequest request) {
        if (validateUserPort.existsByEmailAndNickname(request.getEmail(), request.getNickname()))
            throw CoreExceptionFactory.createBasic(CoreExceptionType.DUPLICATE_USER);

        long userId = saveUserPort.saveUser(SaveUserRequest.builder()
            .email(request.getEmail())
            .nickname(request.getNickname())
            .password(request.getPassword())
            .roleNumber(FundyRole.BACKER.getNumValue())
            .build());

        String number = Account.generateRandomNumber();
        while(findAccountPort.findByNumber(number).isPresent())
            number = Account.generateRandomNumber();

        saveAccountPort.saveAccount(SaveAccountRequest.builder()
                .ownerId(userId)
                .number(number)
                .balance(1000000)
            .build());
        return userId;
    }

    @Override
    public UserInfoResponse getUserInfoByEmail(String email) {
        FundyUser fundyUser = findByEmail(email);

        return UserInfoResponse.builder()
            .profile(fundyUser.getProfile())
            .nickname(fundyUser.getNickname())
            .email(fundyUser.getEmail())
            .id(fundyUser.getId())
            .accounts(fundyUser.getAccounts().stream().map((account) -> UserAccountsResponse.builder()
                    .id(account.getId())
                    .number(account.getNumber())
                    .balance(account.getBalance().getAmount())
                    .build())
                .collect(Collectors.toList()))
            .build();
    }

    private FundyUser findByEmail(String email) {
        return UserAccountMapper.toDomainFrom(findUserPort.findByEmail(email).orElseThrow(
            () -> CoreExceptionFactory.createBasic(CoreExceptionType.NO_USER)));
    }

    @Override
    public SecurityUserInfoResponse getSecurityUserInfo(String email) {
        FundyUser fundyUser = findByEmail(email);

        return SecurityUserInfoResponse.builder()
            .email(fundyUser.getEmail())
            .password(fundyUser.getPassword())
            .role(fundyUser.getRole().name())
            .build();
    }

    @Override
    public DuplicateNicknameResponse checkDuplicateNickname(String nickname) {
        return DuplicateNicknameResponse.builder()
            .isDuplicate(validateUserPort.existsByNickname(nickname))
            .nickname(nickname)
            .build();
    }

    @Override
    public boolean isExistByEmail(String email) {
        return validateUserPort.existsByEmail(email);
    }

    @Override
    public UserInfoConnectorResponse searchUserById(long id) {
        return fromDomain(UserAccountMapper.toDomainFrom(findUserPort.findById(id).orElseThrow(()
            -> CoreExceptionFactory.createBasic(CoreExceptionType.NO_USER))));
    }

    @Override
    public UserInfoConnectorResponse searchUserByEmail(String email) {
        return fromDomain(findByEmail(email));
    }

    private UserInfoConnectorResponse fromDomain(FundyUser fundyUser) {
       return UserInfoConnectorResponse.builder()
            .id(fundyUser.getId())
            .profile(fundyUser.getProfile())
            .nickname(fundyUser.getNickname())
            .build();
    }

    @Override
    public boolean isAccountOwner(String email, long accountId) {
        FundyUser fundyUser = findByEmail(email);
        Account targetAccount = UserAccountMapper.toDomainFrom(findAccountPort.findById(accountId).orElseThrow(()
            -> CoreExceptionFactory.createBasic(CoreExceptionType.NO_OBJECT)));

        return fundyUser.isAccountOwner(targetAccount);
    }
}