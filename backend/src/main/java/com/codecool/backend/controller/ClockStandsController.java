package com.codecool.backend.controller;

import com.codecool.backend.dao.model.ClockStands;
import com.codecool.backend.service.ClockStandsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("clockstands")
public class ClockStandsController {

    private final ClockStandsService clockStandsService;

    @Autowired
    public ClockStandsController(ClockStandsService clockStandsService) {
        this.clockStandsService = clockStandsService;
    }

    @GetMapping("/all")
    public List<ClockStands> getAllClockStandsObjects() throws SQLException {
        return clockStandsService.getAllClockStands();
    }

    @GetMapping("/date")
    public List<ClockStands> getClockStandsByYearAndMonth(@RequestParam int year, Month month) throws SQLException {
        return clockStandsService.getClockStandsByMonthAndYear(year, month);
    }

    @PostMapping("/")
    public ResponseEntity<String> postClockStand(@RequestBody ClockStands clockStands) throws SQLException {
        return clockStandsService.postClockStands(clockStands);
    }
}
