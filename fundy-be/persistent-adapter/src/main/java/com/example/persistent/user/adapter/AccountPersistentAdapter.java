package com.example.persistent.user.adapter;

import com.example.core.application.user.output.FindAccountPort;
import com.example.core.application.user.output.SaveAccountPort;
import com.example.core.application.user.output.dto.req.SaveAccountRequest;
import com.example.core.application.user.output.dto.res.LoadAccountInfoResponse;
import com.example.persistent.user.model.AccountModel;
import com.example.persistent.user.model.UserModel;
import com.example.persistent.user.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountPersistentAdapter implements SaveAccountPort, FindAccountPort {
    private final AccountRepository accountRepository;

    @Override
    public Optional<LoadAccountInfoResponse> findById(final long id) {
        return toLoadAccountInfo(accountRepository.findById(id));
    }

    @Override
    public Optional<LoadAccountInfoResponse> findByNumber(final String number) {
        return toLoadAccountInfo(accountRepository.findByNumber(number));
    }

    @Override
    public Optional<LoadAccountInfoResponse> findByOwner() {
        return Optional.empty();
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

    private Optional<LoadAccountInfoResponse> toLoadAccountInfo(Optional<AccountModel> model) {
        AccountModel account = model.orElse(null);
        if (account == null)
            return Optional.empty();

        return Optional.of(LoadAccountInfoResponse.builder()
                .number(account.getNumber())
                .id(account.getId())
                .balance(account.getBalance())
            .build());
    }
}