package com.example.domain.project;

import com.example.domain.common.vo.Money;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Reward {
    private Long id;
    private String title;
    private String item;
    private String image;
    private Money minimumPrice;
}
