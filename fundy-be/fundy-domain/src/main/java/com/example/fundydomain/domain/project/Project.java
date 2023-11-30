package com.example.fundydomain.domain.project;

import com.example.fundydomain.consists.enums.Genre;
import com.example.fundydomain.domain.user.FundyUser;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "PROJECT")
public class Project {
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

    @Column(name = "thumbnail")
    private String thumbnail;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "GENRES")
    private List<String> genres;

    @Embedded
    private ProjectPeriod projectPeriod;

    @Embedded
    private DevNoteUploadTerm devNoteUploadTerm;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    private FundyUser owner;

    @Builder
    private Project(String description, List<Genre> genres, String title, String content, ProjectPeriod projectPeriod, DevNoteUploadTerm devNoteUploadTerm, FundyUser owner) {
        this.genres = genres.stream().map(Genre::getName).collect(Collectors.toList());
        this.title = title;
        this.description = description;
        this.content = content;
        this.projectPeriod = projectPeriod;
        this.devNoteUploadTerm = devNoteUploadTerm;
        this.owner = owner;
    }

    public List<Genre> getGenres() {
        return genres.stream().map(Genre::nameOf).collect(Collectors.toList());
    }
}
