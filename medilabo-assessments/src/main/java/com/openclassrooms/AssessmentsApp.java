package com.openclassrooms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.openclassrooms.proxy")
public class AssessmentsApp {
    public static void main(String[] args) {
        SpringApplication.run(AssessmentsApp.class, args);
    }
}