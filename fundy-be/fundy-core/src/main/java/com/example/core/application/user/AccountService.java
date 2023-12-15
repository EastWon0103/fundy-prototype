package com.example.core.application.user;

import com.example.core.application.intermiddle.user.AccountConnector;
import com.example.core.application.intermiddle.user.dto.res.AccountConnectorResponse;
import com.example.core.application.user.output.FindAccountPort;
import com.example.core.application.user.output.SaveAccountPort;
import com.example.core.application.user.output.dto.req.UpdateAccountBalanceRequest;
import com.example.core.utils.exception.CoreExceptionFactory;
import com.example.core.utils.exception.CoreExceptionType;
import com.example.domain.common.vo.Money;
import com.example.domain.user.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService implements AccountConnector {
    private final SaveAccountPort saveAccountPort;
    private final FindAccountPort findAccountPort;

    @Override
    public List<AccountConnectorResponse> findByEmail(String email) {
        return findAccountPort.findByOwnerEmail(email)
            .stream().map((account) -> AccountConnectorResponse.builder()
                .id(account.getId())
                .number(account.getNumber())
                .balance(account.getBalance())
                .build())
            .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void depositById(long id, int amount) {
        Account account = UserAccountMapper.toDomainFrom(findAccountPort.findById(id).orElseThrow(
            () -> CoreExceptionFactory.createBasic(CoreExceptionType.NO_OBJECT)));

        account.deposit(Money.of(amount));
        saveAccountPort.updateAccount(UpdateAccountBalanceRequest.of(id, account.getBalance().getAmount()));
    }

    @Transactional
    @Override
    public void withdrawById(long id, int amount) {
        Account account = UserAccountMapper.toDomainFrom(findAccountPort.findById(id).orElseThrow(
            () -> CoreExceptionFactory.createBasic(CoreExceptionType.NO_OBJECT)));

        account.withdraw(Money.of(amount));
        saveAccountPort.updateAccount(UpdateAccountBalanceRequest.of(id, account.getBalance().getAmount()));
    }
}