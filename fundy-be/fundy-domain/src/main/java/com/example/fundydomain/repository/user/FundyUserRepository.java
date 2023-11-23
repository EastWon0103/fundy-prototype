package com.example.fundydomain.repository.user;

import com.example.fundydomain.domain.user.FundyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FundyUserRepository extends JpaRepository<FundyUser, Long> {
    FundyUser save(FundyUser fundyUser);
    Optional<FundyUser> findByEmail(String email);
    Optional<FundyUser> findByNickname(String nickname);
}
