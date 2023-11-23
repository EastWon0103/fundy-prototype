package com.example.fundydomain.consists.enums;

import lombok.Getter;

@Getter
public enum FundyRole {
    BACKER("BACKER"),
    CREATOR("CREATOR"),
    ADMIN("ADMIN");

    private String value;
    FundyRole(String value) {
        this.value = value;
    }
}
