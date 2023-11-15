package com.example.fundyapi.controller;

import com.example.domain.fundyuser.FundyUser;
import com.example.domain.fundyuser.FundyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final FundyUserRepository fundyUserRepository;

    @GetMapping("/test/save")
    public final long test() {
        FundyUser fundyUser = fundyUserRepository.save(FundyUser.builder()
                .name("testname")
            .build());
        return fundyUser.getId();
    }
}
