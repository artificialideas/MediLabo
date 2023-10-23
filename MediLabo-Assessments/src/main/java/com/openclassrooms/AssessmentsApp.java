package com.openclassrooms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AssessmentsApp implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(AssessmentsApp.class);

    public static void main(String[] args) {
        SpringApplication.run(AssessmentsApp.class, args);
    }

    @Override
    public void  run(String... args) throws Exception {
        logger.info("Starting Assessments Service");
    }
}