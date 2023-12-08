package com.toy.realestatebackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class AptTradeController {
    @GetMapping("/api/apartment")
    public List<String> apartment() {
        return Arrays.asList("강서구", "강동구");
    }
}
