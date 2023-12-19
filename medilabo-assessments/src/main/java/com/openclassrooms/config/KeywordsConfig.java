package com.openclassrooms.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
public class KeywordsConfig {
    @Autowired
    private ResourceLoader resourceLoader;

    @Bean
    public List<String> getRiskKeywords() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:keywords.json");
        InputStream inputStream = resource.getInputStream();

        ObjectMapper objectMapper = new ObjectMapper();
        Keywords keywords = objectMapper.readValue(inputStream, Keywords.class);

        return keywords.getRiskKeywords();
    }

    @Getter
    @Setter
    private static class Keywords {
        private List<String> riskKeywords;
    }
}
