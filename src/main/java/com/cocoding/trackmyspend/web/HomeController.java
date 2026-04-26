package com.cocoding.trackmyspend.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/test")
    public String test() {
        return "Spring Boot is working";
    }
}
