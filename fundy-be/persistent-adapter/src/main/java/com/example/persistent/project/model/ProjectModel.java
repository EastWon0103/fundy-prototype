package com.example.persistent.project.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "PROJECT")
public class ProjectModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "THUMBNAIL")
    private String thumbnail;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "GENRES", joinColumns = @JoinColumn(name = "PROJECT_ID"))
    private List<String> genres = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_DATETIME", nullable = false)
    private LocalDateTime startDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_DATETIME", nullable = false)
    private LocalDateTime endDateTime;

    @Column(name = "DEVNOTE_UPLOAD_CYCLE", nullable = false)
    private int weekCycle;

    @Column(name = "DEVNOTE_UPLOAD_DAY", nullable = false)
    private String day;

    @Column(name = "OWNER_ID")
    private Long ownerId;

    @Column(name = "TARGET_AMOUNT")
    private int targetAmount;

    @Column(name = "IS_TRANSACTION_END")
    private boolean isTransactionEnd;

    @Column(name = "DEPOSIT_ACCOUNT_ID")
    private Long depositAccountId;
    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }
    public void setTransactionEnd(boolean isTransactionEnd) {
        this.isTransactionEnd = isTransactionEnd;
    }
}
