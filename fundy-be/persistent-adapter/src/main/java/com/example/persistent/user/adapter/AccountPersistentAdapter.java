package com.example.persistent.user.adapter;

import com.example.core.application.user.output.FindAccountPort;
import com.example.core.application.user.output.SaveAccountPort;
import com.example.core.application.user.output.dto.req.SaveAccountRequest;
import com.example.core.application.user.output.dto.req.UpdateAccountBalanceRequest;
import com.example.core.application.user.output.dto.res.LoadAccountInfoResponse;
import com.example.persistent.user.model.AccountModel;
import com.example.persistent.user.model.UserModel;
import com.example.persistent.user.repository.AccountRepository;
import com.example.persistent.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountPersistentAdapter implements SaveAccountPort, FindAccountPort {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Override
    public Optional<LoadAccountInfoResponse> findById(final long id) {
        return toLoadAccountInfo(accountRepository.findById(id));
    }

    @Override
    public Optional<LoadAccountInfoResponse> findByNumber(final String number) {
        return toLoadAccountInfo(accountRepository.findByNumber(number));
    }

    @Override
    public List<LoadAccountInfoResponse> findByOwnerEmail(String email) {
        UserModel userModel = userRepository.findByEmail(email).orElse(null);
        if (userModel == null)
            return new ArrayList<>();

        return userModel.getAccounts().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public long saveAccount(final SaveAccountRequest request) {
        return accountRepository.save(AccountModel.builder()
                .owner(UserModel.builder()
                    .id(request.getOwnerId())
                    .build())
                .number(request.getNumber())
                .balance(request.getBalance())
            .build()).getId();
    }

    @Override
    public void updateAccount(UpdateAccountBalanceRequest request) {
        AccountModel accountModel = accountRepository.findById(request.getId()).orElse(null);
        if (accountModel != null) {
            accountModel.setBalance(request.getBalance());
            accountRepository.save(accountModel);
        }
    }

    private Optional<LoadAccountInfoResponse> toLoadAccountInfo(Optional<AccountModel> model) {
        AccountModel account = model.orElse(null);
        if (account == null)
            return Optional.empty();

        return Optional.of(mapToDto(account));
    }

    private LoadAccountInfoResponse mapToDto(AccountModel accountModel) {
        return LoadAccountInfoResponse.builder()
            .number(accountModel.getNumber())
            .id(accountModel.getId())
            .balance(accountModel.getBalance())
            .build();
    }
}