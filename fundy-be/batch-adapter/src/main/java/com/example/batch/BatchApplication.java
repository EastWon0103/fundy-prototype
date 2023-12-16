package com.example.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
    "com.example.batch", "com.example.core",
    "com.example.persistent", "com.example.email"})
@EntityScan(basePackages = "com.example.persistent")
@EnableJpaRepositories(basePackages = "com.example.persistent")
public class BatchApplication {
    public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class, args);
    }
}
