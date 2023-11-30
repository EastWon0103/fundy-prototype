package com.example.fundydomain.domain.reward;

import com.example.fundydomain.domain.project.Project;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "REWARD")
@Getter
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "ITEM", nullable = false)
    private String item;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "MINIMUM_PRICE")
    private int minimumPrice;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;
}
