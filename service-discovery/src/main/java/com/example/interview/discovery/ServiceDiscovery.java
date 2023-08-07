package com.example.interview.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ServiceDiscovery {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "discovery-server");
        SpringApplication.run(ServiceDiscovery.class, args);
    }
}
