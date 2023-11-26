package com.example.fundydomain.domain.project.converter;

import com.example.fundydomain.consists.enums.Day;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class DayAttributeConverter implements AttributeConverter<Day, String> {

    @Override
    public String convertToDatabaseColumn(Day attribute) {
        return attribute.getValue();
    }

    @Override
    public Day convertToEntityAttribute(String dbData) {
        return Day.valueOf(dbData);
    }
}
