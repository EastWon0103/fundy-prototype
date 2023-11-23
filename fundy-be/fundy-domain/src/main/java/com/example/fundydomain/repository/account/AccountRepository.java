package com.example.fundydomain.repository.account;

import com.example.fundydomain.domain.account.Account;
import com.example.fundydomain.domain.user.FundyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account save(Account account);
    Optional<Account> findByOwner(FundyUser fundyUser);
    boolean existsByNumber(String number);
}
