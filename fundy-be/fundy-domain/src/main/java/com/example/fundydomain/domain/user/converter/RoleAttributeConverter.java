package com.example.fundydomain.domain.user.converter;

import com.example.fundydomain.consists.enums.FundyRole;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class RoleAttributeConverter implements AttributeConverter<FundyRole, Integer> {
    @Override
    public Integer convertToDatabaseColumn(FundyRole attribute) {
        switch (attribute) {
            case BACKER:
                return 1;
            case CREATOR:
                return 2;
            case ADMIN:
                return 3;
        }
        return null;
    }

    @Override
    public FundyRole convertToEntityAttribute(Integer dbData) {
        switch (dbData) {
            case 1:
                return FundyRole.BACKER;
            case 2:
                return FundyRole.CREATOR;
            case 3:
                return FundyRole.CREATOR;
        }
        return null;
    }
}
