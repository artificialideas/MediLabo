package com.openclassrooms.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("/login")
    public ResponseEntity<Void> login(HttpServletRequest request) {
        // Extract credentials from the request
        String username = request.getHeader(HttpHeaders.AUTHORIZATION);
        String password = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Validate credentials
        if (isValidCredentials(username, password)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private boolean isValidCredentials(String username, String password) {
        return "admin".equals(username) && "medilabo".equals(password);
    }
}