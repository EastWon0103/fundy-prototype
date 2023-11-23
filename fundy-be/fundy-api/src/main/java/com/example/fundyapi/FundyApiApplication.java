package com.example.fundyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.example.fundydomain", "com.example.fundyapi"})
@EntityScan(basePackages = "com.example.fundydomain")
@EnableJpaRepositories(basePackages = "com.example.fundydomain")
public class FundyApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(FundyApiApplication.class, args);
    }
}
