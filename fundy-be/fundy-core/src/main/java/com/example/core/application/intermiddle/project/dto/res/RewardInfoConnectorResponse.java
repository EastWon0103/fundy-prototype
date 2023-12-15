package com.example.core.application.intermiddle.project.dto.res;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class RewardInfoConnectorResponse {
    private long id;
    private String title;
    private String item;
    private String image;
    private int minimumPrice;
    private long projectId;
}