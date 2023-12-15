package com.example.domain.project.vo;

import com.example.domain.project.enums.Day;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class DevNoteUploadTerm {
    private int weekCycle;
    private Day day;

    public static DevNoteUploadTerm of(int weekCycle, Day day) {
        return new DevNoteUploadTerm(weekCycle, day);
    }
}
