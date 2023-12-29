//package com.openclassrooms.config;
//
//import feign.RequestInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//
//@Configuration
//public class FeignConfig {
//    @Bean
//    public RequestInterceptor requestInterceptor() {
//        return requestTemplate -> {
//            // Retrieve security context from the original request
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//            // Pass authentication details to the Feign client request
//            if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
//                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//                requestTemplate.header("Authorization", "Bearer " + userDetails.getUsername());
//            }
//        };
//    }
//}
