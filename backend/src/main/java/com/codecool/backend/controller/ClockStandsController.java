package com.codecool.backend.controller;

import com.codecool.backend.dao.model.ClockStands;
import com.codecool.backend.service.ClockStandsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.Month;
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

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/date")
    public List<ClockStands> getClockStandsByYearAndMonth(@RequestParam int year, Month month) throws SQLException {
        return clockStandsService.getClockStandsByMonthAndYear(year, month);
    }
}
