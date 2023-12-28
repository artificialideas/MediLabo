package com.openclassrooms.controller;

import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {
    @PostMapping("/authenticate")
    public boolean authenticate(@RequestBody User user) {
        return user.getUsername().equals("admin") && user.getPassword().equals("medilabo");
    }
}
