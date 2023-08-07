package com.example.interview.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.interview"})
public class OrganisationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrganisationsApplication.class, args);
    }
}
