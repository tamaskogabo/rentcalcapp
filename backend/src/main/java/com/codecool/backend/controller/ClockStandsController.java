package com.codecool.backend.controller;

import com.codecool.backend.controller.dtos.ClockStandsRequest;
import com.codecool.backend.dao.model.ClockStands;
import com.codecool.backend.service.ClockStandsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Validated
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
    public List<ClockStands> getClockStandsByYearAndMonth(@RequestParam int year,@RequestParam Month month) throws SQLException {
        return clockStandsService.getClockStandsByMonthAndYear(year, month);
    }

    @PostMapping("/")
    public ResponseEntity<String> postClockStand(@Valid @RequestBody ClockStandsRequest request) throws SQLException {
        return clockStandsService.postClockStands(request);
    }
}
