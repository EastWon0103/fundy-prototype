package com.example.fundydomain.domain.project;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ProjectPeriod {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_DATETIME", nullable = false)
    private LocalDateTime startDateTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_DATETIME", nullable = false)
    private LocalDateTime endDateTime;
}
