package com.codecool.backend.service;

import com.codecool.backend.controller.dtos.ClockStandsRequest;
import com.codecool.backend.dao.ClockStandsDao;
import com.codecool.backend.dao.model.ClockStands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClockStandsService {
    private final ClockStandsDao clockStandsDao;

    @Autowired
    public ClockStandsService(ClockStandsDao clockStandsDao) {
        this.clockStandsDao = clockStandsDao;
    }

    public List<ClockStands> getAllClockStands() throws SQLException {
        return clockStandsDao.getAllClockStands();
    }

    public List<ClockStands> getClockStandsByMonthAndYear(int year, Month month) throws SQLException {
        return clockStandsDao.getClockStandByMonthAndYear(year, month);
    }

    public ResponseEntity<String> postClockStands(ClockStandsRequest request) throws SQLException {
        return clockStandsDao.postClockStand(request.createClockStands());
    }
}
