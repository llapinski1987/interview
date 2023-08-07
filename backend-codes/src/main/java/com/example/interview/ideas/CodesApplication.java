package com.example.interview.ideas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.example.interview"})
@EnableScheduling
public class CodesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodesApplication.class, args);
    }
}
