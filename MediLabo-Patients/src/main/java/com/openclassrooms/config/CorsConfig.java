package com.openclassrooms.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CorsConfig {
    /*@Bean
    WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedHeaders("*")
                        .allowedMethods("*")
                        .allowedOrigins("http://localhost:4200");
            }
        };
    }*/
}
