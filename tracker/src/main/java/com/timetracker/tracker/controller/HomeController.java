package com.timetracker.tracker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class HomeController {
    
    @GetMapping(value="/")
    public String welcomeGreeting() {
        return "Welcome User";
    }
    
}

