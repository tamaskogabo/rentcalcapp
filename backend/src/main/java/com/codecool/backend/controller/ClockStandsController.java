package com.codecool.backend.controller;

import com.codecool.backend.dao.model.ClockStands;
import com.codecool.backend.service.ClockStandsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("clockstands")
public class ClockStandsController {

    @Autowired
    private final ClockStandsService clockStandsService;

    public ClockStandsController(ClockStandsService clockStandsService) {
        this.clockStandsService = clockStandsService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/all")
    public List<ClockStands> getAllClockStandsObjects() throws SQLException {
        return clockStandsService.getAllClockStands();
    }
}
