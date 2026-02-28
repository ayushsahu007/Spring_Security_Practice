package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuccessController {
    @GetMapping("/success")
    public String successPage() {
        return "Login successful! Welcome to the Admin Dashboard.";
    }
}
