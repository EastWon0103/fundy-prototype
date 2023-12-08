package com.example.persistent.user.repository;

import com.example.persistent.user.model.AccountModel;
import com.example.persistent.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountModel, Long> {
    @Override
    Optional<AccountModel> findById(Long id);

    Optional<AccountModel> findByOwner(UserModel userModel);

    Optional<AccountModel> findByNumber(String number);
}
