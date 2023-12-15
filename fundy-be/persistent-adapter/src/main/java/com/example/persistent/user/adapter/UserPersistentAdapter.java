package com.example.persistent.user.adapter;

import com.example.core.application.user.output.FindUserPort;
import com.example.core.application.user.output.SaveUserPort;
import com.example.core.application.user.output.ValidateUserPort;
import com.example.core.application.user.output.dto.req.SaveUserRequest;
import com.example.core.application.user.output.dto.res.LoadAccountInfoResponse;
import com.example.core.application.user.output.dto.res.LoadUserInfoResponse;
import com.example.persistent.user.model.UserModel;
import com.example.persistent.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserPersistentAdapter implements SaveUserPort, FindUserPort, ValidateUserPort {
    private final UserRepository userRepository;


    @Override
    public long saveUser(final SaveUserRequest request) {
        UserModel user = userRepository.save(UserModel.builder()
                .email(request.getEmail())
                .nickname(request.getNickname())
                .password(request.getPassword())
                .role(request.getRoleNumber())
            .build());
        return user.getId();
    }

    @Override
    public Optional<LoadUserInfoResponse> findByEmail(String email) {
        UserModel userModel = userRepository.findByEmail(email).orElse(null);
        if(userModel == null)
            return Optional.empty();

        return Optional.of(LoadUserInfoResponse.builder()
                .id(userModel.getId())
                .email(userModel.getEmail())
                .nickname(userModel.getNickname())
                .profile(userModel.getProfile())
                .password(userModel.getPassword())
                .role(userModel.getRole())
                .accounts(userModel.getAccounts().stream().map(account -> LoadAccountInfoResponse.builder()
                        .id(account.getId())
                        .number(account.getNumber())
                        .balance(account.getBalance())
                        .build())
                    .collect(Collectors.toList()))
            .build());
    }

    @Override
    public Optional<LoadUserInfoResponse> findById(long id) {
        UserModel userModel = userRepository.findById(id).orElse(null);
        if (userModel == null)
            return Optional.empty();

        return Optional.of(LoadUserInfoResponse.builder()
            .id(userModel.getId())
            .email(userModel.getEmail())
            .nickname(userModel.getNickname())
            .profile(userModel.getProfile())
            .password(userModel.getPassword())
            .role(userModel.getRole())
            .accounts(userModel.getAccounts().stream().map(account -> LoadAccountInfoResponse.builder()
                    .id(account.getId())
                    .number(account.getNumber())
                    .balance(account.getBalance())
                    .build())
                .collect(Collectors.toList()))
            .build());
    }

    @Override
    public boolean existsByEmailAndNickname(String email, String nickname) {
        return userRepository.existsByEmailOrNickname(email, nickname);
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
