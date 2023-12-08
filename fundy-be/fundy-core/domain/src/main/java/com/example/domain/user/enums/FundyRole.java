package com.example.domain.user.enums;

import lombok.Getter;

@Getter
public enum FundyRole {
    BACKER(1), CREATOR(2), ADMIN(3);

    private int numValue;
    FundyRole(int numValue) {
        this.numValue = numValue;
    }

    public static FundyRole valueOf(int numValue) {
        for(FundyRole role : FundyRole.values()) {
            if(role.numValue == numValue)
                return role;
        }

        return null;
    }
}
