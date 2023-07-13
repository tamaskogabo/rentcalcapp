package com.codecool.backend.controller;

import com.codecool.backend.service.ClockStandsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("clockstands")
public class ClockStandsController {

    @Autowired
    private final ClockStandsService clockStandsService;

    public ClockStandsController(ClockStandsService clockStandsService) {
        this.clockStandsService = clockStandsService;
    }
}
