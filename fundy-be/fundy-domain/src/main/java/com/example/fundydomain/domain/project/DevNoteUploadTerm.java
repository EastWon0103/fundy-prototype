package com.example.fundydomain.domain.project;

import com.example.fundydomain.consists.enums.Day;
import com.example.fundydomain.domain.project.converter.DayAttributeConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class DevNoteUploadTerm {
    @Column(name = "DEVNOTE_UPLOAD_CYCLE", nullable = false)
    private int weekCycle;
    @Convert(converter = DayAttributeConverter.class)
    @Column(name = "DEVNOTE_UPLOAD_DAY", nullable = false)
    private Day day;
}
