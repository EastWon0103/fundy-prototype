package com.example.persistent.user.repository;

import com.example.persistent.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmail(String email);
    boolean existsByEmailOrNickname(String email, String nickname);
    boolean existsByNickname(String nickname);
    boolean existsByEmail(String email);
}