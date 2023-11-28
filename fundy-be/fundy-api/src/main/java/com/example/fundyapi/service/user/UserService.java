package com.example.fundyapi.service.user;

import com.example.fundyapi.common.exception.DuplicateUserException;
import com.example.fundyapi.common.exception.NoUserException;
import com.example.fundyapi.common.utils.token.core.JwtUtil;
import com.example.fundyapi.common.utils.token.user.UserTokenProvider;
import com.example.fundyapi.service.user.dto.request.SignInServiceRequest;
import com.example.fundyapi.service.user.dto.request.SignUpServiceRequest;
import com.example.fundyapi.service.user.dto.response.DuplicateNicknameResponse;
import com.example.fundyapi.service.user.dto.response.SignInResponse;
import com.example.fundyapi.service.user.dto.response.UserAccountsResponse;
import com.example.fundyapi.service.user.dto.response.UserInfoResponse;
import com.example.fundydomain.domain.user.FundyUser;
import com.example.fundydomain.logic.user.FundyUserLogic;
import com.example.fundydomain.logic.user.dto.request.CreateUserLogicRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserUseCase {
    private final FundyUserLogic fundyUserLogic;
    private final UserTokenProvider userTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
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

    @Override
    public SignInResponse signIn(SignInServiceRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        List<String> authorities = authentication.getAuthorities()
            .stream().map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());

        return SignInResponse.builder()
            .grantType(JwtUtil.GRANT_TYPE)
            .token(userTokenProvider.generateToken(authentication.getName(), authorities))
            .build();
    }

    @Override
    public UserInfoResponse getUserInfo(String email) {
        FundyUser user = fundyUserLogic.findByEmail(email).orElseThrow(NoUserException::createBasic);

        return UserInfoResponse.builder()
            .id(user.getId())
            .email(user.getEmail())
            .nickname(user.getNickname())
            .profile(user.getProfile())
            .accounts(user.getAccounts()
                .stream().map(account -> UserAccountsResponse.builder()
                    .id(account.getId())
                    .number(account.getNumber())
                    .balance(account.getBalance())
                    .build())
                .collect(Collectors.toList()))
            .build();
    }
}
