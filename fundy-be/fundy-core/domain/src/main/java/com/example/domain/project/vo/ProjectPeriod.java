package com.example.domain.project.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class ProjectPeriod {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public static ProjectPeriod of(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return new ProjectPeriod(startDateTime, endDateTime);
    }
}
