package com.example.domain.fundyuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundyUserRepository extends JpaRepository<FundyUser, Long> {
    FundyUser save(FundyUser fundyUser);
}
