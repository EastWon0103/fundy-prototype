package com.example.fundydomain.consists.enums;

public enum Genre {
    RPG("RPG"),
    PUZZLE("PUZZLE"),
    TPS("TPS"),
    FPS("FPS"),
    SIMULATION("시뮬레이션"),
    ACTION("액션");
    private String value;
    Genre(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
