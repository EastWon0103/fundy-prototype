package com.example.domain.project.enums;

public enum Day {
    MONDAY("월요일"),
    TUESDAY("화요일"),
    WEDNESDAY("수요일"),
    THURSDAY("목요일"),
    FRIDAY("금요일"),
    SATURDAY("토요일"),
    SUNDAY("일요일");

    private String value;

    Day(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
