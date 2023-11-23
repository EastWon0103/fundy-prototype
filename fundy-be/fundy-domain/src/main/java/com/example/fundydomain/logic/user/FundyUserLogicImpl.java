package com.example.fundydomain.logic.user;

import com.example.fundydomain.consists.enums.FundyRole;
import com.example.fundydomain.domain.account.Account;
import com.example.fundydomain.domain.user.FundyUser;
import com.example.fundydomain.logic.user.dto.request.CreateUserLogicRequest;
import com.example.fundydomain.repository.account.AccountRepository;
import com.example.fundydomain.repository.user.FundyUserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FundyUserLogicImpl implements FundyUserLogic {
    private final FundyUserRepository fundyUserRepository;
    private final AccountRepository accountRepository;

    @Override
    public FundyUser createNormalUser(CreateUserLogicRequest request) {
        FundyUser user = FundyUser.builder()
            .email(request.getEmail())
            .password(request.getPassword())
            .nickname(request.getNickname())
            .role(FundyRole.BACKER)
            .build();

        Account account = Account.builder()
            .number(createAccountNumber())
            .balance(100000)
            .owner(user)
            .build();

        accountRepository.save(account);
        return fundyUserRepository.save(user);
    }

    @Override
    public Optional<FundyUser> findByEmail(String email) {
        return fundyUserRepository.findByEmail(email);
    }

    @Override
    public Optional<FundyUser> findByNickname(String nickname) {
        return fundyUserRepository.findByNickname(nickname);
    }

    private String createAccountNumber() {
        String number = generateAccountNumber();
        while (accountRepository.existsByNumber(number)) {
            number = generateAccountNumber();
        }

        return number;
    }

    private String generateAccountNumber() {
        String number = RandomStringUtils.randomNumeric(11);
        StringBuilder sb = new StringBuilder(number);
        sb.insert(5,"-");
        sb.insert(9,"-");
        return sb.toString();
    }
}
