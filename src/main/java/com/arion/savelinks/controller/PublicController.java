package com.arion.savelinks.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class PublicController {
    @GetMapping("/health-check")
    public String healthCheck(){
        return "All good";
    }
}

