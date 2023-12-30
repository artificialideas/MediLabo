package com.openclassrooms.controller;

import lombok.Getter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {
    @PostMapping("/authenticate")
    public boolean authenticate(@RequestBody User user) {
        return user.getUsername().equals("admin") && user.getPassword().equals("medilabo");
    }

    @Getter
    private static class User {
        private String username;
        private String password;
    }
}
