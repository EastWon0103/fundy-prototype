package com.example.core.application.user.output;

public interface ValidateUserPort {
    boolean existsByEmailAndNickname(String email, String nickname);
    boolean existsByNickname(String nickname);
}
