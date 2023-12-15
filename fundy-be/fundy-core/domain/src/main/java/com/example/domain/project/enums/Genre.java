package com.example.domain.project.enums;

public enum Genre {
    RPG("RPG"),
    PUZZLE("퍼즐"),
    TPS("TPS"),
    FPS("FPS"),
    SIMULATION("시뮬레이션"),
    ACTION("액션");
    private String name;
    Genre(String value) {
        this.name = value;
    }

    public String getName() {
        return name;
    }

    public static Genre nameOf(String name) {
        for(Genre genre: Genre.values()) {
            if(genre.getName().equals(name))
                return genre;
        }

        return null;
    }
}
