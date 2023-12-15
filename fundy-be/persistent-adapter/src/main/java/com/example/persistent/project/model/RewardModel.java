package com.example.persistent.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "REWARD")
public class RewardModel {
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
    private ProjectModel project;
}
