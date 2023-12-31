package com.openclassrooms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotesApp implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(NotesApp.class);

    public static void main(String[] args) {
        SpringApplication.run(NotesApp.class, args);
    }

    @Override
    public void  run(String... args) throws Exception {
        logger.info("Starting Notes Service");
    }
}