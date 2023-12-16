package com.example.domain.project;

import com.example.domain.common.vo.Money;
import com.example.domain.project.enums.Genre;
import com.example.domain.project.vo.DevNoteUploadTerm;
import com.example.domain.project.vo.ProjectPeriod;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Project {
    private long id;
    private String title;
    private String content;
    private String description;
    private String thumbnail;
    private List<Genre> genres;
    private Money targetAmount;
    private List<Reward> rewards;
    private DevNoteUploadTerm devNoteUploadTerm;
    private ProjectPeriod projectPeriod;
    private long depositAccountId;
    private long ownerId;

    public double calculatePercentage(Money totalMoney) {
        return (double) totalMoney.getAmount() / (double) targetAmount.getAmount() * 100;
    }

    public boolean isExpired() {
        return projectPeriod.getEndDateTime().isBefore(LocalDateTime.now());
    }
}
