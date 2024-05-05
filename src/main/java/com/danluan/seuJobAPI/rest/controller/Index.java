package com.danluan.seuJobAPI.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/")
@RestController
public class Index {


    @GetMapping
    public String index() {
        return "Hello World";
    }
}
